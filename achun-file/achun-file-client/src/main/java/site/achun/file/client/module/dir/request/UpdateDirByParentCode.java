package site.achun.file.client.module.dir.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import site.achun.file.client.module.dir.beans.DirInfo;
import site.achun.file.client.module.dir.beans.FileInfo;

import java.util.List;

@Schema(description = "根据父编码更新")
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UpdateDirByParentCode {
    private String parentCode;
    private List<DirInfo> dirs;
    private List<FileInfo> files;

}
