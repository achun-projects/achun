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
 * @TableName channel
 */
@TableName(value ="channel")
@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Channel implements Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 编码
     */
    private String channelCode;

    /**
     * 频道名
     */
    private String name;

    /**
     * 频道描述
     */
    private String description;

    /**
     * 用户编码
     */
    private String userCode;

    /**
     * 封面
     */
    private String coverFileCode;

    /**
     * 横幅
     */
    private String bannerFileCode;

    /**
     * 可见性
     */
    private Integer visibility;

    /**
     * 创建时间
     */
    private LocalDateTime ctime;

    /**
     * 修改时间
     */
    private LocalDateTime utime;

    /**
     * 访问时间
     */
    private LocalDateTime atime;

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
        Channel other = (Channel) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getChannelCode() == null ? other.getChannelCode() == null : this.getChannelCode().equals(other.getChannelCode()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getDescription() == null ? other.getDescription() == null : this.getDescription().equals(other.getDescription()))
            && (this.getUserCode() == null ? other.getUserCode() == null : this.getUserCode().equals(other.getUserCode()))
            && (this.getCoverFileCode() == null ? other.getCoverFileCode() == null : this.getCoverFileCode().equals(other.getCoverFileCode()))
            && (this.getBannerFileCode() == null ? other.getBannerFileCode() == null : this.getBannerFileCode().equals(other.getBannerFileCode()))
            && (this.getVisibility() == null ? other.getVisibility() == null : this.getVisibility().equals(other.getVisibility()))
            && (this.getCtime() == null ? other.getCtime() == null : this.getCtime().equals(other.getCtime()))
            && (this.getUtime() == null ? other.getUtime() == null : this.getUtime().equals(other.getUtime()))
            && (this.getAtime() == null ? other.getAtime() == null : this.getAtime().equals(other.getAtime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getChannelCode() == null) ? 0 : getChannelCode().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getDescription() == null) ? 0 : getDescription().hashCode());
        result = prime * result + ((getUserCode() == null) ? 0 : getUserCode().hashCode());
        result = prime * result + ((getCoverFileCode() == null) ? 0 : getCoverFileCode().hashCode());
        result = prime * result + ((getBannerFileCode() == null) ? 0 : getBannerFileCode().hashCode());
        result = prime * result + ((getVisibility() == null) ? 0 : getVisibility().hashCode());
        result = prime * result + ((getCtime() == null) ? 0 : getCtime().hashCode());
        result = prime * result + ((getUtime() == null) ? 0 : getUtime().hashCode());
        result = prime * result + ((getAtime() == null) ? 0 : getAtime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", channelCode=").append(channelCode);
        sb.append(", name=").append(name);
        sb.append(", description=").append(description);
        sb.append(", userCode=").append(userCode);
        sb.append(", coverFileCode=").append(coverFileCode);
        sb.append(", bannerFileCode=").append(bannerFileCode);
        sb.append(", visibility=").append(visibility);
        sb.append(", ctime=").append(ctime);
        sb.append(", utime=").append(utime);
        sb.append(", atime=").append(atime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}