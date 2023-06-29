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
 * @TableName album_record
 */
@TableName(value ="album_record")
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AlbumRecord implements Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 相册编码
     */
    private String albumCode;

    /**
     * 分组编码
     */
    private String setCode;

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
        AlbumRecord other = (AlbumRecord) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getAlbumCode() == null ? other.getAlbumCode() == null : this.getAlbumCode().equals(other.getAlbumCode()))
            && (this.getSetCode() == null ? other.getSetCode() == null : this.getSetCode().equals(other.getSetCode()))
            && (this.getCtime() == null ? other.getCtime() == null : this.getCtime().equals(other.getCtime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getAlbumCode() == null) ? 0 : getAlbumCode().hashCode());
        result = prime * result + ((getSetCode() == null) ? 0 : getSetCode().hashCode());
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
        sb.append(", albumCode=").append(albumCode);
        sb.append(", setCode=").append(setCode);
        sb.append(", ctime=").append(ctime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}