package site.achun.gallery.client.module.rules.beans;

import lombok.AllArgsConstructor;
import lombok.Getter;
import site.achun.support.api.utils.EnumUtil;

@Getter
@AllArgsConstructor
public enum RuleType {

    SCHEDULED("SCHEDULED","分时段获取"),
    ALBUM_UNIT_RANDOM("ALBUM_UNIT_RANDOM","相册内单元随机"),
    FROM_LISTS("FROM_LISTS","来自多个集合");

    private String code;
    private String name;

    public static RuleType parse(String code){
        return EnumUtil.parse(values(),RuleType::getCode,code).get();
    }
}
