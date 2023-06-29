package site.achun.video.app.generator.mapper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.annotations.Param;
import site.achun.video.app.generator.domain.VideoClickRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import site.achun.video.app.service.execute.record.ObjectNum;

import java.util.Collection;
import java.util.List;

/**
* @author Administrator
* @description 针对表【video_click_record】的数据库操作Mapper
* @createDate 2023-06-29 18:54:59
* @Entity site.achun.video.app.generator.domain.VideoClickRecord
*/
public interface VideoClickRecordMapper extends BaseMapper<VideoClickRecord> {

    List<ObjectNum> selectClickNum(@Param("req") Request request);

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Request{
        private Collection<String> videoCodes;
    }
}




