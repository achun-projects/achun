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
 * @Date: 2022/10/8 18:22
 */
@Getter
@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ViewLevelEnum implements BaseEnum {

    NORMAL(1, "NORMAL"),
    NSFW(2, "NSFW"),
    R18(3, "R18"),
    EXTREME(4, "EXTREME"),
    DISCOMFORT(5, "DISCOMFORT");

    @EnumValue
    private Integer code;

    private String desc;

}
