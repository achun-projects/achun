package site.achun.file.generator.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import lombok.*;
import site.achun.file.client.module.storage.beans.StorageExtra;

/**
 * 
 * @TableName storage
 */
@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value ="storage",autoResultMap = true)
public class Storage implements Serializable {
    /**
     * ID
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 存储名
     */
    private String name;

    /**
     * 存储编码
     */
    private String storageCode;

    /**
     * 所属bucket
     */
    private String bucketCode;

    /**
     * 路径
     */
    private String path;

    /**
     * 访问前缀
     */
    private String accessPrefix;

    /**
     * 1. 正常，2.禁用
     */
    private Integer status;

    /**
     * 额外信息
     */

    @TableField(typeHandler = JacksonTypeHandler.class)
    private StorageExtra extra;

    /**
     * 创建时间
     */
    private LocalDateTime ctime;

    /**
     * 修改时间
     */
    private LocalDateTime utime;

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
        Storage other = (Storage) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getStorageCode() == null ? other.getStorageCode() == null : this.getStorageCode().equals(other.getStorageCode()))
            && (this.getBucketCode() == null ? other.getBucketCode() == null : this.getBucketCode().equals(other.getBucketCode()))
            && (this.getPath() == null ? other.getPath() == null : this.getPath().equals(other.getPath()))
            && (this.getAccessPrefix() == null ? other.getAccessPrefix() == null : this.getAccessPrefix().equals(other.getAccessPrefix()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getExtra() == null ? other.getExtra() == null : this.getExtra().equals(other.getExtra()))
            && (this.getCtime() == null ? other.getCtime() == null : this.getCtime().equals(other.getCtime()))
            && (this.getUtime() == null ? other.getUtime() == null : this.getUtime().equals(other.getUtime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getStorageCode() == null) ? 0 : getStorageCode().hashCode());
        result = prime * result + ((getBucketCode() == null) ? 0 : getBucketCode().hashCode());
        result = prime * result + ((getPath() == null) ? 0 : getPath().hashCode());
        result = prime * result + ((getAccessPrefix() == null) ? 0 : getAccessPrefix().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getExtra() == null) ? 0 : getExtra().hashCode());
        result = prime * result + ((getCtime() == null) ? 0 : getCtime().hashCode());
        result = prime * result + ((getUtime() == null) ? 0 : getUtime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", storageCode=").append(storageCode);
        sb.append(", bucketCode=").append(bucketCode);
        sb.append(", path=").append(path);
        sb.append(", accessPrefix=").append(accessPrefix);
        sb.append(", status=").append(status);
        sb.append(", extra=").append(extra);
        sb.append(", ctime=").append(ctime);
        sb.append(", utime=").append(utime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}