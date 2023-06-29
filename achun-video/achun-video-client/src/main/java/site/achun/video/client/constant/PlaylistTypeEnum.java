package site.achun.video.client.constant;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import site.achun.support.api.enums.BaseEnum;

/**
 * Description
 *
 * @Author: Heiffeng
 * @Date: 2022/11/18 11:32
 **/
@Getter
@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum PlaylistTypeEnum implements BaseEnum {

    CHANNEL(1,"channel"),
    USER(2,"user");

    @EnumValue
    private Integer code;

    private String desc;
}
