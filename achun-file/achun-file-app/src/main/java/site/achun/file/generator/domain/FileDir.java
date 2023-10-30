package site.achun.file.generator.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @TableName file_dir
 */
@Data
@TableName(value ="file_dir")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FileDir implements Serializable {
    /**
     * 主键
     */
    @TableId
    private Integer id;

    /**
     * 路径编码
     */
    private String dirCode;

    /**
     * 父路径编码
     */
    private String parentDirCode;

    /**
     * 路径文件夹名称
     */
    private String name;

    /**
     * 路径地址（Storage内）
     */
    private String path;

    /**
     * 是否存在
     */
    private Integer deleted;

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
        FileDir other = (FileDir) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getDirCode() == null ? other.getDirCode() == null : this.getDirCode().equals(other.getDirCode()))
            && (this.getParentDirCode() == null ? other.getParentDirCode() == null : this.getParentDirCode().equals(other.getParentDirCode()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getPath() == null ? other.getPath() == null : this.getPath().equals(other.getPath()))
            && (this.getDeleted() == null ? other.getDeleted() == null : this.getDeleted().equals(other.getDeleted()))
            && (this.getCtime() == null ? other.getCtime() == null : this.getCtime().equals(other.getCtime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getDirCode() == null) ? 0 : getDirCode().hashCode());
        result = prime * result + ((getParentDirCode() == null) ? 0 : getParentDirCode().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getPath() == null) ? 0 : getPath().hashCode());
        result = prime * result + ((getDeleted() == null) ? 0 : getDeleted().hashCode());
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
        sb.append(", dirCode=").append(dirCode);
        sb.append(", parentDirCode=").append(parentDirCode);
        sb.append(", name=").append(name);
        sb.append(", path=").append(path);
        sb.append(", deleted=").append(deleted);
        sb.append(", ctime=").append(ctime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}