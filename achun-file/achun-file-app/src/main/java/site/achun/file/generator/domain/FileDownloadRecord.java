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
 * @TableName file_download_record
 */
@TableName(value ="file_download_record")
@Data
public class FileDownloadRecord implements Serializable {
    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 文件编码
     */
    private String fileCode;

    /**
     * 分组编码
     */
    private String groupCode;

    /**
     * Bucket编码
     */
    private String bucketCode;

    /**
     * 冗余字段，Bucket本地路径
     */
    private String bucketLocalDir;

    /**
     * 文件在Bucket中的保存路径
     */
    private String filePath;

    /**
     * 下载链接
     */
    private String url;

    /**
     * 本地存在时是否覆盖，1.覆盖。2.不覆盖
     */
    private Integer override;

    /**
     * 代理IP
     */
    private String proxyHost;

    /**
     * 代理端口
     */
    private Integer proxyPort;

    /**
     * 下载耗时，单位：ms
     */
    private Integer costTime;

    /**
     * 文件大小
     */
    private Integer fileSize;

    /**
     * 下载状态，100.下载成功 200.下载失败
101 本地存在文件，覆盖成功
102 本地存在文件，无需覆盖
201 本地存在文件，覆盖失败
     */
    private Integer status;

    /**
     * 状态码
     */
    private String mark;

    /**
     * http下载相应状态码
     */
    private Integer httpStatus;

    /**
     * 下载失败原因说明
     */
    private String description;

    /**
     * 本地是否存在文件，1.存在，2.不存在
     */
    private Integer existsLocalFile;

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
        FileDownloadRecord other = (FileDownloadRecord) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getFileCode() == null ? other.getFileCode() == null : this.getFileCode().equals(other.getFileCode()))
            && (this.getGroupCode() == null ? other.getGroupCode() == null : this.getGroupCode().equals(other.getGroupCode()))
            && (this.getBucketCode() == null ? other.getBucketCode() == null : this.getBucketCode().equals(other.getBucketCode()))
            && (this.getBucketLocalDir() == null ? other.getBucketLocalDir() == null : this.getBucketLocalDir().equals(other.getBucketLocalDir()))
            && (this.getFilePath() == null ? other.getFilePath() == null : this.getFilePath().equals(other.getFilePath()))
            && (this.getUrl() == null ? other.getUrl() == null : this.getUrl().equals(other.getUrl()))
            && (this.getOverride() == null ? other.getOverride() == null : this.getOverride().equals(other.getOverride()))
            && (this.getProxyHost() == null ? other.getProxyHost() == null : this.getProxyHost().equals(other.getProxyHost()))
            && (this.getProxyPort() == null ? other.getProxyPort() == null : this.getProxyPort().equals(other.getProxyPort()))
            && (this.getCostTime() == null ? other.getCostTime() == null : this.getCostTime().equals(other.getCostTime()))
            && (this.getFileSize() == null ? other.getFileSize() == null : this.getFileSize().equals(other.getFileSize()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getMark() == null ? other.getMark() == null : this.getMark().equals(other.getMark()))
            && (this.getHttpStatus() == null ? other.getHttpStatus() == null : this.getHttpStatus().equals(other.getHttpStatus()))
            && (this.getDescription() == null ? other.getDescription() == null : this.getDescription().equals(other.getDescription()))
            && (this.getExistsLocalFile() == null ? other.getExistsLocalFile() == null : this.getExistsLocalFile().equals(other.getExistsLocalFile()))
            && (this.getCtime() == null ? other.getCtime() == null : this.getCtime().equals(other.getCtime()))
            && (this.getUtime() == null ? other.getUtime() == null : this.getUtime().equals(other.getUtime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getFileCode() == null) ? 0 : getFileCode().hashCode());
        result = prime * result + ((getGroupCode() == null) ? 0 : getGroupCode().hashCode());
        result = prime * result + ((getBucketCode() == null) ? 0 : getBucketCode().hashCode());
        result = prime * result + ((getBucketLocalDir() == null) ? 0 : getBucketLocalDir().hashCode());
        result = prime * result + ((getFilePath() == null) ? 0 : getFilePath().hashCode());
        result = prime * result + ((getUrl() == null) ? 0 : getUrl().hashCode());
        result = prime * result + ((getOverride() == null) ? 0 : getOverride().hashCode());
        result = prime * result + ((getProxyHost() == null) ? 0 : getProxyHost().hashCode());
        result = prime * result + ((getProxyPort() == null) ? 0 : getProxyPort().hashCode());
        result = prime * result + ((getCostTime() == null) ? 0 : getCostTime().hashCode());
        result = prime * result + ((getFileSize() == null) ? 0 : getFileSize().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getMark() == null) ? 0 : getMark().hashCode());
        result = prime * result + ((getHttpStatus() == null) ? 0 : getHttpStatus().hashCode());
        result = prime * result + ((getDescription() == null) ? 0 : getDescription().hashCode());
        result = prime * result + ((getExistsLocalFile() == null) ? 0 : getExistsLocalFile().hashCode());
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
        sb.append(", fileCode=").append(fileCode);
        sb.append(", groupCode=").append(groupCode);
        sb.append(", bucketCode=").append(bucketCode);
        sb.append(", bucketLocalDir=").append(bucketLocalDir);
        sb.append(", filePath=").append(filePath);
        sb.append(", url=").append(url);
        sb.append(", override=").append(override);
        sb.append(", proxyHost=").append(proxyHost);
        sb.append(", proxyPort=").append(proxyPort);
        sb.append(", costTime=").append(costTime);
        sb.append(", fileSize=").append(fileSize);
        sb.append(", status=").append(status);
        sb.append(", mark=").append(mark);
        sb.append(", httpStatus=").append(httpStatus);
        sb.append(", description=").append(description);
        sb.append(", existsLocalFile=").append(existsLocalFile);
        sb.append(", ctime=").append(ctime);
        sb.append(", utime=").append(utime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}