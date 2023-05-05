package site.achun.support.api.utils;

import site.achun.support.api.exception.LogicException;
import site.achun.support.api.response.RC;
import site.achun.support.api.response.RspCodeInfo;

/**
 * Description
 *
 * @Author: Heiffeng
 * @Date: 2022/3/19 0:24
 */
public class Assert {

    public static void is(boolean is, RspCodeInfo rspCodeInfo){
        if(is) throw new LogicException(rspCodeInfo);
    }

    public static void is(boolean is,String message){
        if(is) throw new LogicException(RC.FAILED_VALIDATION,message);
    }

    public static void is(boolean is,String template,String...args){
        if(is) throw new LogicException(RC.FAILED_VALIDATION,String.format(template,args));
    }

    public static void not(boolean is,RspCodeInfo rspCodeInfo){
        if(!is) throw new LogicException(rspCodeInfo);
    }

    public static void not(boolean is,String message){
        if(!is) throw new LogicException(RC.FAILED_VALIDATION,message);
    }

    public static void not(boolean is,String template,String...args){
        if(!is) throw new LogicException(RC.FAILED_VALIDATION,String.format(template,args));
    }
}
