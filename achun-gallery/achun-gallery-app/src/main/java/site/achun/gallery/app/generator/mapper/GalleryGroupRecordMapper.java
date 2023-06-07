package site.achun.gallery.app.generator.mapper;

import org.apache.ibatis.annotations.Param;
import site.achun.gallery.app.generator.domain.GalleryGroupRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import site.achun.gallery.client.dto.GroupRecordView;

import java.util.List;

/**
* @author Administrator
* @description 针对表【gallery_group_record】的数据库操作Mapper
* @createDate 2023-05-29 17:17:29
* @Entity site.achun.gallery.app.generator.domain.GalleryGroupRecord
*/
public interface GalleryGroupRecordMapper extends BaseMapper<GalleryGroupRecord> {

    List<GroupRecordView> selectBoardRecord(@Param("userCode") String userCode);


    List<GroupRecordView> selectAlbumRecord(@Param("userCode") String userCode);
}




