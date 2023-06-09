package site.achun.gallery.app.controller.board;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import site.achun.gallery.client.module.board.BoardUpdateClient;
@Slf4j
@Tag(name = "画板更新")
@RestController
@RequiredArgsConstructor
public class BoardUpdateController implements BoardUpdateClient {

}
