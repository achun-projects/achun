package site.achun.gallery.client.module.pic_unit.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Collection;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QueryByFileCodes implements Serializable {

    @Schema(title = "文件编码集合")
    private Collection<String> fileCodes;

    @Schema(title = "用户编码")
    private String userCode;

}
