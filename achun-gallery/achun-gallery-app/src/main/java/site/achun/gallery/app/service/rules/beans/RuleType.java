package site.achun.gallery.app.service.rules.beans;

import lombok.AllArgsConstructor;
import lombok.Getter;
import site.achun.support.api.utils.EnumUtil;

@Getter
@AllArgsConstructor
public enum RuleType {

    FROM_LISTS("FROM_LISTS","来自多个集合");
    private String code;
    private String name;

    public static RuleType parse(String code){
        return EnumUtil.parse(values(),RuleType::getCode,code).get();
    }
}
