package site.achun.gallery.app.service.board;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.achun.gallery.app.generator.domain.Board;
import site.achun.gallery.app.generator.domain.BoardRecord;
import site.achun.gallery.app.generator.service.BoardRecordService;
import site.achun.gallery.app.generator.service.BoardService;
import site.achun.gallery.app.generator.service.GalleryGroupRecordService;
import site.achun.gallery.app.service.gallery_group.GroupRecordUpdateExecute;
import site.achun.gallery.app.service.gallery_group.MyGalleryGroupService;
import site.achun.gallery.app.service.gallery_group.MyGroupService;
import site.achun.gallery.app.service.pictures.MyPictureService;
import site.achun.gallery.app.utils.DateTimeUtil;
import site.achun.gallery.client.module.board.request.BoardCreateRequest;
import site.achun.gallery.client.module.board.request.QueryBoardPage;
import site.achun.gallery.client.module.board.response.BoardResponse;
import site.achun.support.api.response.RspPage;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Description
 *
 * @Author: Heiffeng
 * @Date: 2022/1/9 16:55
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class MyBoardService {


    private final BoardService boardService;
    private final MyGalleryGroupService myGalleryGroupService;
    private final GalleryGroupRecordService groupRecordService;
    private final BoardRecordService boardRecordService;
    private final MyPictureService myPictureService;

    private final GroupRecordUpdateExecute groupRecordUpdateExecute;
    private final MyGroupService myGroupService;

    public void updateRecordUtime(String boardCode){
        boardService.lambdaUpdate()
                .eq(Board::getBoardCode,boardCode)
                .set(Board::getRecordUtime, LocalDateTime.now())
                .update();
    }


    public BoardResponse create(BoardCreateRequest createRequest) {
        Board board = toBoard(createRequest);
        boardService.save(board);
        // 保存分组信息
        groupRecordUpdateExecute.createRecord(createRequest.getGroupCodes(),board.getBoardCode());
        return toBoardResponse(board);
    }

    public BoardResponse selectByBoardCode(String boardCode){
        Board board = boardService.lambdaQuery()
                .eq(Board::getBoardCode, boardCode)
                .one();
        return toBoardResponse(board);
    }

    public RspPage<BoardResponse> queryPage(QueryBoardPage query) {
        Set<String> listCodes = groupRecordService.queryListCodes(query.getGroup());
        RspPage<BoardResponse> rspPage = query.createPageRsp();
        Page<Board> pageData = boardService.lambdaQuery()
                .eq(Board::getUserCode, query.getUserCode())
                .in(CollUtil.isNotEmpty(listCodes),Board::getBoardCode,listCodes)
                .orderByDesc(Board::getRecordUtime)
                .page(Page.of(query.getPage(), query.getSize()));
        if(pageData == null || CollectionUtil.isEmpty(pageData.getRecords())){
            return query.createPageRsp();
        }
        rspPage.setTotal(pageData.getTotal());
        rspPage.setRows(pageData.getRecords().stream()
                .map(this::toBoardResponse)
                .collect(Collectors.toList())
        );
        Set<String> albumCodes = pageData.getRecords().stream()
                .map(Board::getBoardCode)
                .collect(Collectors.toSet());
        Map<String, Integer> albumCountMap = myPictureService.queryBoardFileCounts(albumCodes);
        rspPage.getRows().stream()
                .forEach(row -> row.setFileCount(albumCountMap.get(row.getBoardCode())));
        return rspPage;
    }



    public BoardResponse toBoardResponse(Board board) {
        if(board == null) return null;
        BoardResponse boardResponse = BeanUtil.toBean(board, BoardResponse.class);
        // 查询画板文件总数和最后更新时间
        Long count = boardRecordService.lambdaQuery()
                .eq(BoardRecord::getBoardCode, board.getBoardCode())
                .count();
        boardResponse.setFileCount(count.intValue());
        boardResponse.setRecentAtime(DateTimeUtil.parse(board.getRecordUtime()));
        // 填充预览图
        List<BoardRecord> records = boardRecordService.lambdaQuery()
                .eq(BoardRecord::getBoardCode, board.getBoardCode())
                .last("limit 3")
                .orderByDesc(BoardRecord::getCtime)
                .list();
        if(CollUtil.isNotEmpty(records)){
            List<String> previews = records.stream()
                    .map(BoardRecord::getFileCode)
                    .collect(Collectors.toList());
            boardResponse.setPreviews(previews);
        }else{
            boardResponse.setPreviews(new ArrayList<>());
        }
        // 填充分组名
        String groupNames = myGroupService.selectForNames(board.getBoardCode()).getGroupNames();
        boardResponse.setGroupNames(groupNames);

        return boardResponse;
    }

    private Board toBoard(BoardCreateRequest createRequest) {
        return Board.builder()
                .boardCode(UUID.randomUUID().toString().replace("-",""))
                .description(createRequest.getDescription())
                .name(createRequest.getName())
                .userCode(createRequest.getUserCode())
                .ctime(LocalDateTime.now()).utime(LocalDateTime.now())
                .recordUtime(LocalDateTime.now())
                .build();
    }
}
