package site.achun.file.client.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import site.achun.support.api.utils.EnumUtil;

/**
 * Description
 *
 * @Author: Heiffeng
 * @Date: 2022/2/28 16:05
 **/
@Getter
@AllArgsConstructor
public enum Env {

    LOCAL("LOCAL"),
    LAN("LAN"),
    NET("NET");

    private String code;

    public final static String KEY = "ENV";

    public final static Env parse(String code){
        return EnumUtil.parse(Env.values(),Env::getCode,code).get();
    }
}
