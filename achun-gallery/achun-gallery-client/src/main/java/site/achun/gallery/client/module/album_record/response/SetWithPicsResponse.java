package site.achun.gallery.client.module.album_record.response;

import lombok.Data;
import site.achun.gallery.client.module.pictures.response.Photo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class SetWithPicsResponse implements Serializable {
    /**
     * 分组唯一标识
     */
    private String code;

    /**
     * 用户code
     */
    private String userCode;

    /**
     * 人物code
     对应dusty_person表，
     如果dusty_person表没有数据则说明没有此人物信息，仅用来标记同一个人
     */
    private String personCode;

    /**
     * 分组名称
     */
    private String name;

    /**
     * 创作者
     */
    private String author;

    /**
     * 1. 正常，所有年龄段均可观看
     2. 暴露，NSFW
     3. 限制级，R18
     4. 夸张，轻微SM
     5. 引起不适，SM，重口

     */
    private Integer viewLevel;

    /**
     * 元数据
     */
    private Object orgin;

    /**
     * 创建时间
     */
    private LocalDateTime ctime;

    /**
     * 修改时间
     */
    private LocalDateTime utime;


    private List<Photo> photos;

    private Integer photosCount;
}
