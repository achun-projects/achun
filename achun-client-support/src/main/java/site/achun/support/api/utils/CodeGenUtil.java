package site.achun.support.api.utils;

import java.util.UUID;

/**
 * Description
 *
 * @Author: Heiffeng
 * @Date: 2022/11/11 14:36
 */
public class CodeGenUtil {

    public static String uuid(){
        return UUID.randomUUID().toString().replace("-","");
    }
}
