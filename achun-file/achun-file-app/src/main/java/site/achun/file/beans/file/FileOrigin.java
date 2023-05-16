package site.achun.file.beans.file;

import lombok.Data;

@Data
public class FileOrigin {
    private String key;
    private String url;
    private String group;
    private String bucket;
    private String thirdId;
    private String fileCode;
    private Long linetime;
    private String path;
    private Object origin;
    private boolean override;
    private String groupCode;
    private String small_url;
    private String bucketCode;
    private String medium_url;
}
