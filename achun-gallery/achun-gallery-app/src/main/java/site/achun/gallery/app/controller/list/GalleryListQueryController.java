package site.achun.gallery.app.controller.list;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import site.achun.gallery.app.service.list.ListRandomQueryService;
import site.achun.gallery.client.module.list.GalleryListQueryClient;

import java.util.Collection;

@Slf4j
@Tag(name = "列表查询")
@RestController
@RequiredArgsConstructor
public class GalleryListQueryController implements GalleryListQueryClient {

    private final ListRandomQueryService listRandomQueryService;

    @Override
    public String randomQuery(Collection<String> listCodes) {
        return listRandomQueryService.randomQuery(listCodes);
    }


}
