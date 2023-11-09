package site.achun.gallery.app.generator.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import lombok.*;
import lombok.experimental.Accessors;
import site.achun.gallery.app.generator.response.AlbumExtra;

/**
 * 相册表
 * @TableName album
 */
@TableName(value ="album",autoResultMap = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
public class Album implements Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Object id;

    /**
     * 相册编码
     */
    private String albumCode;

    /**
     * 相册名
     */
    private String name;

    /**
     * 相册描述
     */
    private String description;

    /**
     * 来源
     */
    private String source;

    /**
     * 用户编码
     */
    private String userCode;

    /**
     * 是否为隐藏相册，1：隐藏，2：非隐藏
     */
    private Integer hide;

    /**
     * 相册封面，为fileCode
     */
    private String coverFileCode;

    /**
     * Banner图
     */
    private String bannerFileCode;

    /**
     * 创建时间
     */
    private LocalDateTime ctime;

    /**
     * 修改时间
     */
    private LocalDateTime utime;

    /**
     * 相册内记录最后更新时间
     */
    private LocalDateTime recordUtime;

    /**
     * 文件总数
     */
    private Integer fileCount;

    /**
     * 分组总数
     */
    private Integer unitCount;

    /**
     * 额外数据
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private AlbumExtra extra;

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
        Album other = (Album) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getAlbumCode() == null ? other.getAlbumCode() == null : this.getAlbumCode().equals(other.getAlbumCode()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getDescription() == null ? other.getDescription() == null : this.getDescription().equals(other.getDescription()))
            && (this.getSource() == null ? other.getSource() == null : this.getSource().equals(other.getSource()))
            && (this.getUserCode() == null ? other.getUserCode() == null : this.getUserCode().equals(other.getUserCode()))
            && (this.getHide() == null ? other.getHide() == null : this.getHide().equals(other.getHide()))
            && (this.getCoverFileCode() == null ? other.getCoverFileCode() == null : this.getCoverFileCode().equals(other.getCoverFileCode()))
            && (this.getBannerFileCode() == null ? other.getBannerFileCode() == null : this.getBannerFileCode().equals(other.getBannerFileCode()))
            && (this.getCtime() == null ? other.getCtime() == null : this.getCtime().equals(other.getCtime()))
            && (this.getUtime() == null ? other.getUtime() == null : this.getUtime().equals(other.getUtime()))
            && (this.getRecordUtime() == null ? other.getRecordUtime() == null : this.getRecordUtime().equals(other.getRecordUtime()))
            && (this.getFileCount() == null ? other.getFileCount() == null : this.getFileCount().equals(other.getFileCount()))
            && (this.getUnitCount() == null ? other.getUnitCount() == null : this.getUnitCount().equals(other.getUnitCount()))
            && (this.getExtra() == null ? other.getExtra() == null : this.getExtra().equals(other.getExtra()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getAlbumCode() == null) ? 0 : getAlbumCode().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getDescription() == null) ? 0 : getDescription().hashCode());
        result = prime * result + ((getSource() == null) ? 0 : getSource().hashCode());
        result = prime * result + ((getUserCode() == null) ? 0 : getUserCode().hashCode());
        result = prime * result + ((getHide() == null) ? 0 : getHide().hashCode());
        result = prime * result + ((getCoverFileCode() == null) ? 0 : getCoverFileCode().hashCode());
        result = prime * result + ((getBannerFileCode() == null) ? 0 : getBannerFileCode().hashCode());
        result = prime * result + ((getCtime() == null) ? 0 : getCtime().hashCode());
        result = prime * result + ((getUtime() == null) ? 0 : getUtime().hashCode());
        result = prime * result + ((getRecordUtime() == null) ? 0 : getRecordUtime().hashCode());
        result = prime * result + ((getFileCount() == null) ? 0 : getFileCount().hashCode());
        result = prime * result + ((getUnitCount() == null) ? 0 : getUnitCount().hashCode());
        result = prime * result + ((getExtra() == null) ? 0 : getExtra().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", albumCode=").append(albumCode);
        sb.append(", name=").append(name);
        sb.append(", description=").append(description);
        sb.append(", source=").append(source);
        sb.append(", userCode=").append(userCode);
        sb.append(", hide=").append(hide);
        sb.append(", coverFileCode=").append(coverFileCode);
        sb.append(", bannerFileCode=").append(bannerFileCode);
        sb.append(", ctime=").append(ctime);
        sb.append(", utime=").append(utime);
        sb.append(", recordUtime=").append(recordUtime);
        sb.append(", fileCount=").append(fileCount);
        sb.append(", unitCount=").append(unitCount);
        sb.append(", extra=").append(extra);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}