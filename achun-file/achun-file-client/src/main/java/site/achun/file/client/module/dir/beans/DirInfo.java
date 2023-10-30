package site.achun.file.client.module.dir.beans;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DirInfo {
    private String dirCode;
    private String parentCode;
    private String storageCode;
    private String name;
    private String path;
}
