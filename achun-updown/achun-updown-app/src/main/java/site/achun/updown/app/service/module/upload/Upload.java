package site.achun.updown.app.service.module.upload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Upload {
    private String storageCode;
    private String unitCode;
    private String key;
    private String path;
    private Boolean override;
    private Map<String, Object> origin;

}
