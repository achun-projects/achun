package site.achun.file.generator.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 
 * @TableName file_info
 */
@TableName(value ="file_info")
@Data
public class FileInfo implements Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 文件唯一标识，系统生成，25年内不重复
     */
    private String fileCode;

    /**
     * 第三方唯一标识ID
     */
    private String thirdId;

    /**
     * 文件MD5码
     */
    private String md5;

    /**
     * 文件仓库标识
     */
    private String storageCode;

    /**
     * 文件名
     */
    private String fileName;

    /**
     * 分组ID，当group相同时，视为一组资源。
这个字段可以灵活使用。
     */
    private String unitCode;

    /**
     * 文件类型，
0.其他
1.图片(jpg,jpeg,gif,png) 
2.视频(mp4,flv) 
3.音频(mp3)
     */
    private Integer type;

    /**
     * 文件后缀
     */
    private String suffix;

    /**
     * 在bucket内的存储路径
     */
    private String inStoragePath;

    /**
     * 封面图片，在bucket内的存储路径
     */
    private String cover;

    /**
     * 文件大小，以单位KB计算
     */
    private Long size;

    /**
     * 宽度，图片视频等格式特有
     */
    private Integer width;

    /**
     * 高度，图片视频等格式特有
     */
    private Integer height;

    /**
     * 宽高比，乘100的整数

     */
    private Integer wh;

    /**
     * 持续时长，视频音频等格式特有
     */
    private Integer duration;

    /**
     * 1. 隐藏文件，2. 非隐藏文件
     */
    private Integer hidden;

    /**
     * 文件源数据
     */
    private Object origin;

    /**
     * 是否删除，1删除，2未删除
     */
    private Integer deleted;

    /**
     * 创建时间
     */
    private LocalDateTime ctime;

    /**
     * 修改时间
     */
    private LocalDateTime utime;

    /**
     * 最后访问时间
     */
    private LocalDateTime atime;

    /**
     * Timeline time。记录文件产生那一刻的时间点
     */
    private LocalDateTime lineTime;

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
        FileInfo other = (FileInfo) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getFileCode() == null ? other.getFileCode() == null : this.getFileCode().equals(other.getFileCode()))
            && (this.getThirdId() == null ? other.getThirdId() == null : this.getThirdId().equals(other.getThirdId()))
            && (this.getMd5() == null ? other.getMd5() == null : this.getMd5().equals(other.getMd5()))
            && (this.getStorageCode() == null ? other.getStorageCode() == null : this.getStorageCode().equals(other.getStorageCode()))
            && (this.getFileName() == null ? other.getFileName() == null : this.getFileName().equals(other.getFileName()))
            && (this.getUnitCode() == null ? other.getUnitCode() == null : this.getUnitCode().equals(other.getUnitCode()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getSuffix() == null ? other.getSuffix() == null : this.getSuffix().equals(other.getSuffix()))
            && (this.getInStoragePath() == null ? other.getInStoragePath() == null : this.getInStoragePath().equals(other.getInStoragePath()))
            && (this.getCover() == null ? other.getCover() == null : this.getCover().equals(other.getCover()))
            && (this.getSize() == null ? other.getSize() == null : this.getSize().equals(other.getSize()))
            && (this.getWidth() == null ? other.getWidth() == null : this.getWidth().equals(other.getWidth()))
            && (this.getHeight() == null ? other.getHeight() == null : this.getHeight().equals(other.getHeight()))
            && (this.getWh() == null ? other.getWh() == null : this.getWh().equals(other.getWh()))
            && (this.getDuration() == null ? other.getDuration() == null : this.getDuration().equals(other.getDuration()))
            && (this.getHidden() == null ? other.getHidden() == null : this.getHidden().equals(other.getHidden()))
            && (this.getOrigin() == null ? other.getOrigin() == null : this.getOrigin().equals(other.getOrigin()))
            && (this.getDeleted() == null ? other.getDeleted() == null : this.getDeleted().equals(other.getDeleted()))
            && (this.getCtime() == null ? other.getCtime() == null : this.getCtime().equals(other.getCtime()))
            && (this.getUtime() == null ? other.getUtime() == null : this.getUtime().equals(other.getUtime()))
            && (this.getAtime() == null ? other.getAtime() == null : this.getAtime().equals(other.getAtime()))
            && (this.getLineTime() == null ? other.getLineTime() == null : this.getLineTime().equals(other.getLineTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getFileCode() == null) ? 0 : getFileCode().hashCode());
        result = prime * result + ((getThirdId() == null) ? 0 : getThirdId().hashCode());
        result = prime * result + ((getMd5() == null) ? 0 : getMd5().hashCode());
        result = prime * result + ((getStorageCode() == null) ? 0 : getStorageCode().hashCode());
        result = prime * result + ((getFileName() == null) ? 0 : getFileName().hashCode());
        result = prime * result + ((getUnitCode() == null) ? 0 : getUnitCode().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getSuffix() == null) ? 0 : getSuffix().hashCode());
        result = prime * result + ((getInStoragePath() == null) ? 0 : getInStoragePath().hashCode());
        result = prime * result + ((getCover() == null) ? 0 : getCover().hashCode());
        result = prime * result + ((getSize() == null) ? 0 : getSize().hashCode());
        result = prime * result + ((getWidth() == null) ? 0 : getWidth().hashCode());
        result = prime * result + ((getHeight() == null) ? 0 : getHeight().hashCode());
        result = prime * result + ((getWh() == null) ? 0 : getWh().hashCode());
        result = prime * result + ((getDuration() == null) ? 0 : getDuration().hashCode());
        result = prime * result + ((getHidden() == null) ? 0 : getHidden().hashCode());
        result = prime * result + ((getOrigin() == null) ? 0 : getOrigin().hashCode());
        result = prime * result + ((getDeleted() == null) ? 0 : getDeleted().hashCode());
        result = prime * result + ((getCtime() == null) ? 0 : getCtime().hashCode());
        result = prime * result + ((getUtime() == null) ? 0 : getUtime().hashCode());
        result = prime * result + ((getAtime() == null) ? 0 : getAtime().hashCode());
        result = prime * result + ((getLineTime() == null) ? 0 : getLineTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", fileCode=").append(fileCode);
        sb.append(", thirdId=").append(thirdId);
        sb.append(", md5=").append(md5);
        sb.append(", storageCode=").append(storageCode);
        sb.append(", fileName=").append(fileName);
        sb.append(", unitCode=").append(unitCode);
        sb.append(", type=").append(type);
        sb.append(", suffix=").append(suffix);
        sb.append(", inStoragePath=").append(inStoragePath);
        sb.append(", cover=").append(cover);
        sb.append(", size=").append(size);
        sb.append(", width=").append(width);
        sb.append(", height=").append(height);
        sb.append(", wh=").append(wh);
        sb.append(", duration=").append(duration);
        sb.append(", hidden=").append(hidden);
        sb.append(", origin=").append(origin);
        sb.append(", deleted=").append(deleted);
        sb.append(", ctime=").append(ctime);
        sb.append(", utime=").append(utime);
        sb.append(", atime=").append(atime);
        sb.append(", lineTime=").append(lineTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}