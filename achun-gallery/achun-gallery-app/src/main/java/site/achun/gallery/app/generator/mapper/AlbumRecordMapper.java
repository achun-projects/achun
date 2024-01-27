package site.achun.gallery.app.generator.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import site.achun.gallery.app.generator.domain.AlbumRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import site.achun.gallery.app.generator.domain.FileSet;
import site.achun.gallery.app.generator.domain.Pictures;
import site.achun.gallery.client.module.album_record.request.QueryPageOfSetWithPics;
import site.achun.gallery.client.module.pictures.request.QueryRecord;

/**
* @author Administrator
* @description 针对表【album_record】的数据库操作Mapper
* @createDate 2023-05-29 17:17:29
* @Entity site.achun.gallery.app.generator.domain.AlbumRecord
*/
public interface AlbumRecordMapper extends BaseMapper<AlbumRecord> {

    int replaceInto(@Param("ar") AlbumRecord record);


    IPage<FileSet> selectPageOfSet(Page<?> page, @Param("query")QueryPageOfSetWithPics query);

}




