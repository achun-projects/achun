package site.achun.gallery.app.generator.mapper;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;
import site.achun.gallery.app.generator.domain.Pictures;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import site.achun.gallery.client.dto.ListFileCount;
import site.achun.gallery.client.module.pictures.request.QueryByListCodes;
import site.achun.gallery.client.module.pictures.request.QueryFilesByListCodes;
import site.achun.gallery.client.module.pictures.request.QueryRecord;
import site.achun.gallery.client.module.pictures.response.TimelineResponse;

import java.util.Map;

/**
* @author Administrator
* @description 针对表【pictures】的数据库操作Mapper
* @createDate 2023-05-29 17:17:29
* @Entity site.achun.gallery.app.generator.domain.Pictures
*/
public interface PicturesMapper extends BaseMapper<Pictures> {

    IPage<Pictures> selectAlbumFiles(Page<?> page, @Param("query") QueryRecord queryRecord);

    IPage<Pictures> selectBoardFiles(Page<?> page, @Param("query") QueryRecord queryRecord);

    IPage<TimelineResponse> selectTimelinePage(Page<?> page);

    int replaceInto(Pictures pictures);

    @MapKey("code")
    Map<String, ListFileCount> selectAlbumsFileCount(@Param("query") QueryByListCodes listCodes);

    @MapKey("code")
    Map<String,ListFileCount> selectBoardFileCount(@Param("query") QueryByListCodes listCodes);


    IPage<Pictures> selectFilesByListCodes(Page<?> page, @Param("query") QueryFilesByListCodes query);
}




