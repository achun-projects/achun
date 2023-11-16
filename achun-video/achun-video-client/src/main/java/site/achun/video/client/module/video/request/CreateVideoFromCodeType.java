package site.achun.video.client.module.video.request;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import site.achun.support.api.enums.BaseEnum;

@Getter
@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum CreateVideoFromCodeType implements BaseEnum {
    DIRS(1),
    FILES(2),
    PARENT_DIR(3);


    @EnumValue
    private Integer code;

    public Integer getType() {
        return code;
    }

}
