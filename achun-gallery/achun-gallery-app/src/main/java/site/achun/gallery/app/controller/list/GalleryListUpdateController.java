package site.achun.gallery.app.controller.list;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import site.achun.gallery.app.service.ablum.AlbumUpdateService;
import site.achun.gallery.app.service.board.BoardUpdateService;
import site.achun.gallery.app.utils.UserInfo;
import site.achun.gallery.client.module.album.request.CreateOrUpdateAlbum;
import site.achun.gallery.client.module.board.request.CreateOrUpdateBoard;
import site.achun.gallery.client.module.list.GalleryListUpdateClient;
import site.achun.gallery.client.module.list.request.ListType;
import site.achun.gallery.client.module.list.request.UpdateCover;
import site.achun.gallery.client.module.list.request.UpdateListBaseInfo;
import site.achun.support.api.response.Rsp;

@Slf4j
@Tag(name = "列表更改")
@RestController
@RequiredArgsConstructor
public class GalleryListUpdateController implements GalleryListUpdateClient {

    private final AlbumUpdateService albumUpdateService;
    private final BoardUpdateService boardUpdateService;
    @Override
    public Rsp updateCover(UpdateCover updateCover) {
        String userCode = UserInfo.getCode();
        if(ListType.ALBUM.getCode().equals(updateCover.getListType())){
            return Rsp.success(albumUpdateService.createOrUpdate(CreateOrUpdateAlbum.builder()
                    .albumCode(updateCover.getCode())
                    .coverFileCode(updateCover.getCoverFileCode())
                    .userCode(userCode)
                    .build()));
        }else if(ListType.BOARD.getCode().equals(updateCover.getListType())){
            return boardUpdateService.updateBoard(CreateOrUpdateBoard.builder()
                    .boardCode(updateCover.getCode())
                    .cover(updateCover.getCoverFileCode())
                    .userCode(userCode)
                    .build());
        }
        return Rsp.error("listType参数错误");
    }

    @Override
    public Rsp updateListBaseInfo(UpdateListBaseInfo update) {
        String userCode = UserInfo.getCode();
        if(ListType.ALBUM.getCode().equals(update.getListType())){
            return Rsp.success(albumUpdateService.createOrUpdate(CreateOrUpdateAlbum.builder()
                    .albumCode(update.getCode())
                    .userCode(userCode)
                    .name(update.getName())
                    .description(update.getDescription())
                    .build()));
        }else if(ListType.BOARD.getCode().equals(update.getListType())){
            return boardUpdateService.updateBoard(CreateOrUpdateBoard.builder()
                    .boardCode(update.getCode())
                    .userCode(userCode)
                    .name(update.getName())
                    .description(update.getDescription())
                    .build());
        }
        return Rsp.error("listType参数错误");
    }
}
