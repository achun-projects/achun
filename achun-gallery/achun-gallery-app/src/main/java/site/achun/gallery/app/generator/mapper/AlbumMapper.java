package site.achun.gallery.app.generator.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import site.achun.gallery.app.generator.domain.Album;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import site.achun.gallery.client.module.album.request.QueryAlbumPage;

import java.util.List;

/**
* @author Administrator
* @description 针对表【album(相册表)】的数据库操作Mapper
* @createDate 2023-11-09 15:30:56
* @Entity site.achun.gallery.app.generator.domain.Album
*/
public interface AlbumMapper extends BaseMapper<Album> {

    Page<Album> queryPage(IPage page, @Param("query") QueryAlbumPage query);

}




