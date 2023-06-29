package site.achun.video.app.generator.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.*;
import site.achun.video.client.constant.PlaylistTypeEnum;

/**
 * 
 * @TableName play_list
 */
@TableName(value ="play_list")
@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlayList implements Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 列表编码
     */
    private String plistCode;

    /**
     * 对象编码
     */
    private String objectCode;

    /**
     * 对象类型，1.用户，2.频道
     */
    @TableField(value = "object_type")
    private PlaylistTypeEnum objectType;
    /**
     * 名称
     */
    private String name;

    /**
     * 描述
     */
    private String description;

    /**
     * 创建时间
     */
    private LocalDateTime ctime;

    /**
     * 更新时间
     */
    private LocalDateTime utime;

    /**
     * 记录更新时间
     */
    private LocalDateTime ruTime;

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
        PlayList other = (PlayList) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getPlistCode() == null ? other.getPlistCode() == null : this.getPlistCode().equals(other.getPlistCode()))
            && (this.getObjectCode() == null ? other.getObjectCode() == null : this.getObjectCode().equals(other.getObjectCode()))
            && (this.getObjectType() == null ? other.getObjectType() == null : this.getObjectType().equals(other.getObjectType()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getDescription() == null ? other.getDescription() == null : this.getDescription().equals(other.getDescription()))
            && (this.getCtime() == null ? other.getCtime() == null : this.getCtime().equals(other.getCtime()))
            && (this.getUtime() == null ? other.getUtime() == null : this.getUtime().equals(other.getUtime()))
            && (this.getRuTime() == null ? other.getRuTime() == null : this.getRuTime().equals(other.getRuTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getPlistCode() == null) ? 0 : getPlistCode().hashCode());
        result = prime * result + ((getObjectCode() == null) ? 0 : getObjectCode().hashCode());
        result = prime * result + ((getObjectType() == null) ? 0 : getObjectType().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getDescription() == null) ? 0 : getDescription().hashCode());
        result = prime * result + ((getCtime() == null) ? 0 : getCtime().hashCode());
        result = prime * result + ((getUtime() == null) ? 0 : getUtime().hashCode());
        result = prime * result + ((getRuTime() == null) ? 0 : getRuTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", plistCode=").append(plistCode);
        sb.append(", objectCode=").append(objectCode);
        sb.append(", objectType=").append(objectType);
        sb.append(", name=").append(name);
        sb.append(", description=").append(description);
        sb.append(", ctime=").append(ctime);
        sb.append(", utime=").append(utime);
        sb.append(", ruTime=").append(ruTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}