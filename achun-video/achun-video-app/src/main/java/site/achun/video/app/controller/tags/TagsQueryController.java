package site.achun.video.app.controller.tags;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import site.achun.support.api.response.Rsp;
import site.achun.video.app.generator.mapper.TagsMapMapper;
import site.achun.video.client.module.tags.request.QueryByChannelRequest;
import site.achun.video.client.module.tags.response.EasyTagsResponse;

import java.util.List;

/**
 * Description
 *
 * @Author: Heiffeng
 * @Date: 2022/10/9 15:49
 */
@Slf4j
@Tag(name = "标签查询")
@RestController
@AllArgsConstructor
public class TagsQueryController {

    private final TagsMapMapper tagsMapMapper;

    @Operation(summary = "根据频道编码查询标签")
    @PostMapping("/video/tags-query/by-channel-code")
    public Rsp<List<EasyTagsResponse>> queryTagsByChannelCode(@RequestBody QueryByChannelRequest request){
        return Rsp.success(tagsMapMapper.selectChannelTags(request));
    }

}
