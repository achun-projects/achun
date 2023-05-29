package site.achun.file.client.module.unit.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class UnitResponse implements Serializable {
    private String unitCode;
    private String unitName;
}
