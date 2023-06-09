package site.achun.gallery.app.controller.board;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import site.achun.gallery.app.service.board.BoardUpdateService;
import site.achun.gallery.app.utils.UserInfo;
import site.achun.gallery.client.module.board.BoardUpdateClient;
import site.achun.gallery.client.module.board.request.BoardCreateRequest;
import site.achun.gallery.client.module.board.request.RemoveBoardRequest;
import site.achun.gallery.client.module.board.response.BoardResponse;
import site.achun.support.api.response.Rsp;

@Slf4j
@Tag(name = "画板更新")
@RestController
@RequiredArgsConstructor
public class BoardUpdateController implements BoardUpdateClient {

    private final BoardUpdateService boardUpdateService;

    @Override
    public Rsp<Void> removeBoard(RemoveBoardRequest removeBoard) {
        return boardUpdateService.removeBoard(removeBoard.getBoardCode());
    }

    @Override
    public Rsp<BoardResponse> createBoard(BoardCreateRequest createRequest) {
        createRequest.setUserCode(UserInfo.getCode(createRequest::getUserCode));
        return boardUpdateService.create(createRequest);
    }
}
