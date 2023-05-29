package site.achun.gallery.client.module.unit.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class FileUnitResponse implements Serializable {

    private String unitCode;
    private String unitName;
}
