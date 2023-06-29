package site.achun.video.app.controller.board;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import site.achun.video.app.service.board.BoardUpdateService;
import site.achun.video.app.utils.UserInfo;
import site.achun.video.client.module.board.BoardUpdateClient;
import site.achun.video.client.module.board.request.CreateOrUpdateBoard;
import site.achun.video.client.module.board.request.RemoveBoardRequest;
import site.achun.video.client.module.board.response.BoardResponse;
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
    public Rsp<BoardResponse> createBoard(CreateOrUpdateBoard createRequest) {
        createRequest.setUserCode(UserInfo.getCode(createRequest::getUserCode));
        return boardUpdateService.create(createRequest);
    }

    @Override
    public Rsp<BoardResponse> createOrUpdateBoard(CreateOrUpdateBoard request) {
        request.setUserCode(UserInfo.getCode(request::getUserCode));
        return boardUpdateService.createOrUpdate(request);
    }
}
