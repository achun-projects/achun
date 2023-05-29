package site.achun.gallery.app.generator.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 画板表
 * @TableName board
 */
@TableName(value ="board")
@Data
public class Board implements Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Object id;

    /**
     * 相册编码
     */
    private String boardCode;

    /**
     * 画板名
     */
    private String name;

    /**
     * 画板描述
     */
    private String description;

    /**
     * 用户编码
     */
    private String userCode;

    /**
     * 是否为隐藏画板，1：隐藏，2：非隐藏
     */
    private Integer hide;

    /**
     * 封面图，多个，用逗号分割
     */
    private String coverFileCodes;

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
     * 画板内记录最后更新时间
     */
    private LocalDateTime recordUtime;

    /**
     * 文件总数
     */
    private Integer fileCount;

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
        Board other = (Board) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getBoardCode() == null ? other.getBoardCode() == null : this.getBoardCode().equals(other.getBoardCode()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getDescription() == null ? other.getDescription() == null : this.getDescription().equals(other.getDescription()))
            && (this.getUserCode() == null ? other.getUserCode() == null : this.getUserCode().equals(other.getUserCode()))
            && (this.getHide() == null ? other.getHide() == null : this.getHide().equals(other.getHide()))
            && (this.getCoverFileCodes() == null ? other.getCoverFileCodes() == null : this.getCoverFileCodes().equals(other.getCoverFileCodes()))
            && (this.getBannerFileCode() == null ? other.getBannerFileCode() == null : this.getBannerFileCode().equals(other.getBannerFileCode()))
            && (this.getCtime() == null ? other.getCtime() == null : this.getCtime().equals(other.getCtime()))
            && (this.getUtime() == null ? other.getUtime() == null : this.getUtime().equals(other.getUtime()))
            && (this.getRecordUtime() == null ? other.getRecordUtime() == null : this.getRecordUtime().equals(other.getRecordUtime()))
            && (this.getFileCount() == null ? other.getFileCount() == null : this.getFileCount().equals(other.getFileCount()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getBoardCode() == null) ? 0 : getBoardCode().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getDescription() == null) ? 0 : getDescription().hashCode());
        result = prime * result + ((getUserCode() == null) ? 0 : getUserCode().hashCode());
        result = prime * result + ((getHide() == null) ? 0 : getHide().hashCode());
        result = prime * result + ((getCoverFileCodes() == null) ? 0 : getCoverFileCodes().hashCode());
        result = prime * result + ((getBannerFileCode() == null) ? 0 : getBannerFileCode().hashCode());
        result = prime * result + ((getCtime() == null) ? 0 : getCtime().hashCode());
        result = prime * result + ((getUtime() == null) ? 0 : getUtime().hashCode());
        result = prime * result + ((getRecordUtime() == null) ? 0 : getRecordUtime().hashCode());
        result = prime * result + ((getFileCount() == null) ? 0 : getFileCount().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", boardCode=").append(boardCode);
        sb.append(", name=").append(name);
        sb.append(", description=").append(description);
        sb.append(", userCode=").append(userCode);
        sb.append(", hide=").append(hide);
        sb.append(", coverFileCodes=").append(coverFileCodes);
        sb.append(", bannerFileCode=").append(bannerFileCode);
        sb.append(", ctime=").append(ctime);
        sb.append(", utime=").append(utime);
        sb.append(", recordUtime=").append(recordUtime);
        sb.append(", fileCount=").append(fileCount);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}