package site.achun.file.client.module.unit.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class UpdateUnit implements Serializable {

    private String unitCode;
    private String unitName;
}
