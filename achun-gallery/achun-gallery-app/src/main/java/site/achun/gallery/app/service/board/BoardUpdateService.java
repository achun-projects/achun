package site.achun.gallery.app.service.board;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import site.achun.gallery.app.generator.domain.Pictures;
import site.achun.gallery.app.generator.domain.Board;
import site.achun.gallery.app.generator.domain.BoardRecord;
import site.achun.gallery.app.generator.mapper.BoardMapper;
import site.achun.gallery.app.generator.mapper.PicturesMapper;
import site.achun.gallery.app.generator.service.BoardRecordService;
import site.achun.gallery.app.generator.service.BoardService;
import site.achun.gallery.client.constant.GalleryRC;
import site.achun.gallery.client.module.board.request.BoardCreateRequest;
import site.achun.gallery.client.module.board.request.BoardUpdateRequest;
import site.achun.gallery.client.module.board.response.BoardResponse;
import site.achun.gallery.client.module.pictures.request.QueryRecord;
import site.achun.support.api.response.Rsp;

import java.time.LocalDateTime;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardUpdateService {

    private final PicturesMapper picturesMapper;
    private final BoardMapper boardMapper;
    private final MyBoardService myBoardService;
    private final BoardService boardService;
    private final BoardRecordService boardRecordService;

    public Rsp<Void> removeBoard(String boardCode) {
        // 检测相册是否为空
        QueryRecord queryRecord = new QueryRecord();
        queryRecord.setCodes(Set.of(boardCode));
        IPage<Pictures> pageResp = picturesMapper.selectBoardFiles(Page.of(1,10), queryRecord);
        if(pageResp.getTotal() > 0){
            return Rsp.error(GalleryRC.EXISTS,"画板内容不为空，无法删除");
        }
        boardService.lambdaUpdate()
                .eq(Board::getBoardCode,boardCode)
                .remove();
        boardRecordService.lambdaUpdate()
                .eq(BoardRecord::getBoardCode,boardCode)
                .remove();
        return Rsp.success(null,"成功删除");
    }

    public Rsp<BoardResponse> create(BoardCreateRequest createRequest) {
        Board board = new LambdaQueryChainWrapper<>(boardMapper)
                .eq(Board::getName, createRequest.getName())
                .eq(Board::getUserCode, createRequest.getUserCode())
                .one();
        if(board != null){
            log.info("用户创建画板时提交了重复名称，userCode:{},name:{}",
                    createRequest.getUserCode(),createRequest.getName());
            return Rsp.error(GalleryRC.EXISTS,"该用户已存在此画板，请修改画板名后重新提交创建");
        }
        BoardResponse boardResponse = myBoardService.create(createRequest);
        return Rsp.success(boardResponse);
    }

    public Rsp<BoardResponse> updateBoard(BoardUpdateRequest updateRequest) {
        Board board = boardService.getByCode(updateRequest.getBoardCode(),updateRequest.getUserCode());
        if(board == null){
            return Rsp.error(GalleryRC.NOT_EXISTS);
        }
        boardService.lambdaUpdate()
                .eq(Board::getBoardCode,updateRequest.getBoardCode())
                .set(StrUtil.isNotEmpty(updateRequest.getCover()),
                        Board::getCoverFileCodes,updateRequest.getCover())
                .set(StrUtil.isNotEmpty(updateRequest.getName()),
                        Board::getName,updateRequest.getName())
                .set(StrUtil.isNotEmpty(updateRequest.getDescription()),
                        Board::getDescription,updateRequest.getDescription())
                .set(Board::getUtime, LocalDateTime.now())
                .set(Board::getRecordUtime,LocalDateTime.now())
                .update();
        return Rsp.success(
                myBoardService.toBoardResponse(boardService.getByCode(updateRequest.getBoardCode())),
                "修改成功"
        );
    }

}
