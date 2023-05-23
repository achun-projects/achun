package site.achun.file.client.module.storage.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StorageStatus {

    ACTIVE(1,"正常"),
    READONLY(2,"只读");

    private Integer code;
    private String desc;

}
