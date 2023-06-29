package site.achun.video.client.module.unit.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class UpdateFileUnit implements Serializable {
    private String unitCode;
    private String unitName;
}
