package site.achun.gallery.app.controller.pictures;

import cn.hutool.core.bean.BeanUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import site.achun.gallery.client.module.pictures.response.PictureResponse;
import site.achun.file.client.module.file.MediaFileQueryClient;
import site.achun.file.client.module.file.request.QueryByFileCodes;
import site.achun.file.client.module.file.response.MediaFileResponse;
import site.achun.gallery.app.service.pictures.TimelineQueryService;
import site.achun.gallery.app.utils.PageUtil;
import site.achun.gallery.client.module.pictures.TimelineQueryClient;
import site.achun.gallery.client.module.pictures.request.QueryTimelinePage;
import site.achun.gallery.client.module.pictures.response.Photo;
import site.achun.gallery.client.module.pictures.response.Timeline;
import site.achun.gallery.client.module.pictures.response.TimelineResponse;
import site.achun.support.api.response.Rsp;
import site.achun.support.api.response.RspPage;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Tag(name = "列表查询")
@RestController
@RequiredArgsConstructor
public class TimelineQueryController implements TimelineQueryClient {

    private final TimelineQueryService timelineQueryService;
    private final MediaFileQueryClient fileQueryClient;
    @Override
    public Rsp<RspPage<Timeline>> queryTimeline(QueryTimelinePage query) {
        Rsp<RspPage<TimelineResponse>> queryPageResponse = timelineQueryService.query(query);
        RspPage<TimelineResponse> rspPage = queryPageResponse.getData();

        List<String> fileCodes = rspPage.getRows().stream()
                .flatMap(rsp -> rsp.getPictures().stream())
                .map(PictureResponse::getFileCode)
                .collect(Collectors.toList());
        Map<String, MediaFileResponse> fileMap = fileQueryClient.queryFileMap(QueryByFileCodes.builder().fileCodes(fileCodes).build()).getData();

        RspPage<Timeline> page = PageUtil.parse(rspPage, rsp -> {
            Timeline timeline = new Timeline();
            timeline.setTime(rsp.getTime());
            timeline.setCount(rsp.getCount());
            List<Photo> photos = rsp.getPictures().stream()
                    .map(pic -> toPhoto(fileMap.get(pic.getFileCode())))
                    .collect(Collectors.toList());
            timeline.setPhotos(photos);
            return timeline;
        });
        return Rsp.success(page);
    }

    private Photo toPhoto(MediaFileResponse file){
        Photo photo = BeanUtil.toBean(file, Photo.class);
        return photo;
    }
}
