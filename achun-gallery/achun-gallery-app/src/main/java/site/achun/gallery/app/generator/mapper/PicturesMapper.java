package site.achun.gallery.app.generator.mapper;


import site.achun.gallery.app.generator.domain.Pictures;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author Administrator
* @description 针对表【pictures】的数据库操作Mapper
* @createDate 2023-05-29 17:17:29
* @Entity site.achun.gallery.app.generator.domain.Pictures
*/
public interface PicturesMapper extends BaseMapper<Pictures> {

//    IPage<Pictures> selectAlbumFiles(Page<?> page, @Param("query") QueryRecord queryRecord);
//
//    IPage<Pictures> selectBoardFiles(Page<?> page, @Param("query") QueryRecord queryRecord);
//
//    IPage<TimelineResponse> selectTimelinePage(Page<?> page);

    void replaceInto(Pictures pictures);

//    @MapKey("code")
//    Map<String,ListFileCount> selectAlbumsFileCount(@Param("query") QueryByListCodes listCodes);
//
//    @MapKey("code")
//    Map<String,ListFileCount> selectBoardFileCount(@Param("query") QueryByListCodes listCodes);
}




