package site.achun.video.app.generator.mapper;

import org.apache.ibatis.annotations.Param;
import site.achun.video.app.generator.domain.TagsMap;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import site.achun.video.client.module.tags.request.QueryByChannelRequest;
import site.achun.video.client.module.tags.request.QueryByPlistCodeRequest;
import site.achun.video.client.module.tags.response.EasyTagsResponse;

import java.util.List;

/**
* @author Administrator
* @description 针对表【tags_map】的数据库操作Mapper
* @createDate 2023-06-29 18:54:59
* @Entity site.achun.video.app.generator.domain.TagsMap
*/
public interface TagsMapMapper extends BaseMapper<TagsMap> {

    List<EasyTagsResponse> selectChannelTags(@Param("request") QueryByChannelRequest request);

    List<EasyTagsResponse> selectPlaylistTags(@Param("request") QueryByPlistCodeRequest request);
}




