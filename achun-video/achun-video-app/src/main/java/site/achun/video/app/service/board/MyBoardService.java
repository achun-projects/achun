package site.achun.video.app.service.board;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import site.achun.video.app.generator.domain.Board;
import site.achun.video.app.generator.service.BoardService;
import site.achun.video.app.generator.service.GalleryGroupRecordService;
import site.achun.video.app.service.gallery_group.GroupRecordUpdateExecute;
import site.achun.video.app.service.pictures.MyPictureService;
import site.achun.video.client.module.board.request.CreateOrUpdateBoard;
import site.achun.video.client.module.board.request.QueryBoardPage;
import site.achun.video.client.module.board.response.BoardResponse;
import site.achun.support.api.response.RspPage;

import java.time.LocalDateTime;
import java.util.*;

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
    private final GalleryGroupRecordService groupRecordService;
    private final MyPictureService myPictureService;

    private final GroupRecordUpdateExecute groupRecordUpdateExecute;

    private final BoardConvert boardConvert;

    public void updateRecordUtime(String boardCode){
        boardService.lambdaUpdate()
                .eq(Board::getBoardCode,boardCode)
                .set(Board::getRecordUtime, LocalDateTime.now())
                .update();
    }


    public BoardResponse create(CreateOrUpdateBoard createRequest) {
        Board board = boardConvert.toBoard(createRequest);
        boardService.save(board);
        // 保存分组信息
        groupRecordUpdateExecute.createRecord(createRequest.getGroupCode(),board.getBoardCode());
        return boardConvert.toBoardResponse(board);
    }

    public BoardResponse selectByBoardCode(String boardCode){
        Board board = boardService.lambdaQuery()
                .eq(Board::getBoardCode, boardCode)
                .one();
        return boardConvert.toBoardResponse(board);
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
        rspPage.setRows(boardConvert.toBoardResponseList(pageData.getRecords()));
        return rspPage;
    }

}
