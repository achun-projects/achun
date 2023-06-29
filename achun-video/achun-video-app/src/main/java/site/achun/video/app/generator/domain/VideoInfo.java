package site.achun.video.app.generator.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.*;
import site.achun.video.client.constant.ViewLevelEnum;

/**
 * 
 * @TableName video_info
 */
@TableName(value ="video_info")
@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VideoInfo implements Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 视频编码
     */
    private String videoCode;

    /**
     * 频道编码
     */
    private String channelCode;

    /**
     * 视频封面
     */
    private String coverFileCode;

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
     * 视频标题
     */
    private String title;

    /**
     * 视频简介
     */
    private String description;

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
    @TableField(value = "view_level")
    private ViewLevelEnum viewLevel;

    /**
     * 元数据
     */
    private Object orgin;

    /**
     * 来源类型，0.自制，1.转载
     */
    private Integer sourceType;

    /**
     * 转载地址
     */
    private String sourceUrl;

    /**
     * 创建时间
     */
    private LocalDateTime ctime;

    /**
     * 修改时间
     */
    private LocalDateTime utime;

    /**
     * 是否删除
     */
    private Integer deleted;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        VideoInfo other = (VideoInfo) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getVideoCode() == null ? other.getVideoCode() == null : this.getVideoCode().equals(other.getVideoCode()))
            && (this.getChannelCode() == null ? other.getChannelCode() == null : this.getChannelCode().equals(other.getChannelCode()))
            && (this.getCoverFileCode() == null ? other.getCoverFileCode() == null : this.getCoverFileCode().equals(other.getCoverFileCode()))
            && (this.getUserCode() == null ? other.getUserCode() == null : this.getUserCode().equals(other.getUserCode()))
            && (this.getPersonCode() == null ? other.getPersonCode() == null : this.getPersonCode().equals(other.getPersonCode()))
            && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
            && (this.getDescription() == null ? other.getDescription() == null : this.getDescription().equals(other.getDescription()))
            && (this.getAuthor() == null ? other.getAuthor() == null : this.getAuthor().equals(other.getAuthor()))
            && (this.getViewLevel() == null ? other.getViewLevel() == null : this.getViewLevel().equals(other.getViewLevel()))
            && (this.getOrgin() == null ? other.getOrgin() == null : this.getOrgin().equals(other.getOrgin()))
            && (this.getSourceType() == null ? other.getSourceType() == null : this.getSourceType().equals(other.getSourceType()))
            && (this.getSourceUrl() == null ? other.getSourceUrl() == null : this.getSourceUrl().equals(other.getSourceUrl()))
            && (this.getCtime() == null ? other.getCtime() == null : this.getCtime().equals(other.getCtime()))
            && (this.getUtime() == null ? other.getUtime() == null : this.getUtime().equals(other.getUtime()))
            && (this.getDeleted() == null ? other.getDeleted() == null : this.getDeleted().equals(other.getDeleted()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getVideoCode() == null) ? 0 : getVideoCode().hashCode());
        result = prime * result + ((getChannelCode() == null) ? 0 : getChannelCode().hashCode());
        result = prime * result + ((getCoverFileCode() == null) ? 0 : getCoverFileCode().hashCode());
        result = prime * result + ((getUserCode() == null) ? 0 : getUserCode().hashCode());
        result = prime * result + ((getPersonCode() == null) ? 0 : getPersonCode().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        result = prime * result + ((getDescription() == null) ? 0 : getDescription().hashCode());
        result = prime * result + ((getAuthor() == null) ? 0 : getAuthor().hashCode());
        result = prime * result + ((getViewLevel() == null) ? 0 : getViewLevel().hashCode());
        result = prime * result + ((getOrgin() == null) ? 0 : getOrgin().hashCode());
        result = prime * result + ((getSourceType() == null) ? 0 : getSourceType().hashCode());
        result = prime * result + ((getSourceUrl() == null) ? 0 : getSourceUrl().hashCode());
        result = prime * result + ((getCtime() == null) ? 0 : getCtime().hashCode());
        result = prime * result + ((getUtime() == null) ? 0 : getUtime().hashCode());
        result = prime * result + ((getDeleted() == null) ? 0 : getDeleted().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", videoCode=").append(videoCode);
        sb.append(", channelCode=").append(channelCode);
        sb.append(", coverFileCode=").append(coverFileCode);
        sb.append(", userCode=").append(userCode);
        sb.append(", personCode=").append(personCode);
        sb.append(", title=").append(title);
        sb.append(", description=").append(description);
        sb.append(", author=").append(author);
        sb.append(", viewLevel=").append(viewLevel);
        sb.append(", orgin=").append(orgin);
        sb.append(", sourceType=").append(sourceType);
        sb.append(", sourceUrl=").append(sourceUrl);
        sb.append(", ctime=").append(ctime);
        sb.append(", utime=").append(utime);
        sb.append(", deleted=").append(deleted);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}