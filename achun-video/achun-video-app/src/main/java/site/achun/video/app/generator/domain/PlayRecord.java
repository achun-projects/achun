package site.achun.video.app.generator.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.*;

/**
 * 
 * @TableName play_record
 */
@TableName(value ="play_record")
@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlayRecord implements Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 用户编码
     */
    private String userCode;

    /**
     * 视频文件编码
     */
    private String videoFileCode;

    /**
     * 播放时长
     */
    private Integer playLongTime;

    /**
     * 视频播放位置
     */
    private Integer videoIndex;

    /**
     * 创建时间
     */
    private LocalDateTime ctime;

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
        PlayRecord other = (PlayRecord) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUserCode() == null ? other.getUserCode() == null : this.getUserCode().equals(other.getUserCode()))
            && (this.getVideoFileCode() == null ? other.getVideoFileCode() == null : this.getVideoFileCode().equals(other.getVideoFileCode()))
            && (this.getPlayLongTime() == null ? other.getPlayLongTime() == null : this.getPlayLongTime().equals(other.getPlayLongTime()))
            && (this.getVideoIndex() == null ? other.getVideoIndex() == null : this.getVideoIndex().equals(other.getVideoIndex()))
            && (this.getCtime() == null ? other.getCtime() == null : this.getCtime().equals(other.getCtime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUserCode() == null) ? 0 : getUserCode().hashCode());
        result = prime * result + ((getVideoFileCode() == null) ? 0 : getVideoFileCode().hashCode());
        result = prime * result + ((getPlayLongTime() == null) ? 0 : getPlayLongTime().hashCode());
        result = prime * result + ((getVideoIndex() == null) ? 0 : getVideoIndex().hashCode());
        result = prime * result + ((getCtime() == null) ? 0 : getCtime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userCode=").append(userCode);
        sb.append(", videoFileCode=").append(videoFileCode);
        sb.append(", playLongTime=").append(playLongTime);
        sb.append(", videoIndex=").append(videoIndex);
        sb.append(", ctime=").append(ctime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}