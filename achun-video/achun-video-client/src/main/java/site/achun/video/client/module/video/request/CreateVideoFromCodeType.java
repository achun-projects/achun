package site.achun.video.client.module.video.request;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum CreateVideoFromCodeType {
    DIRS(1),
    FILES(2),
    PARENT_DIR(3);


    @EnumValue
    private Integer type;

    public Integer getType() {
        return type;
    }
}
