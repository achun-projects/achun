package site.achun.file.api.modules.storage.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum StorageStatus {

    AVAILABLE(1,""),
    CLOSED(2,"");

    @EnumValue
    private Integer code;
    private String desc;

}
