package site.achun.support.api.response;

import cn.hutool.core.util.StrUtil;
import site.achun.support.api.exception.RspException;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * RPC通过返回类
 *
 * @Author: SunAo
 * @Date: 2020/11/12 13:34
 */
public class Rsp<T> implements Serializable {
    private static final long serialVersionUID = -2024625419803956644L;

    public final static Integer CODE_SUCCESS = 0;
    public final static Integer CODE_ERROR_COMMON = 1; // 通用错误码，用于不区分详细错误类型的场景

    private Integer code;
    private String mark;
    private String info;
    private T data;
    private Map<String,Object> values;

    /**
     * Rsp对象封装方法，请求成功时使用
     * code 默认为 0
     * @param data 请求需要返回的数据
     * @param info 返回提示信息
     * @param <T> 返回数据的类型
     * @return Rsp对象
     */
    public static <T> Rsp<T> success(T data, String info){
        Rsp<T> result = new Rsp<>();
        result.code = CODE_SUCCESS;
        result.info = info;
        result.data = data;
        return result;
    }

    /**
     * Rsp对象封装方法，请求成功时使用
     * code 默认为 0
     * info 默认为 空字符串
     * @param data 请求需要返回的数据
     * @param <T> 返回数据的类型
     * @return Rsp对象
     */
    public static <T> Rsp<T> success(T data){
        return success(data,"");
    }

    /**
     * Rsp对象封装方法，请求失败时使用
     * code 默认为 1
     * @param info 提示信息
     * @param <T> 数据类型
     * @return Rsp对象
     */
    public static <T> Rsp<T> error(String info){
        return error(info,CODE_ERROR_COMMON);
    }

    /**
     * Rsp对象封装方法，请求失败时使用
     * @param info 提示信息
     * @param code 错误码code
     * @param <T> 数据类型
     * @return Rsp对象
     */
    public static <T> Rsp<T> error(String info,Integer code){
        if(CODE_SUCCESS.equals(code)){
            throw new IllegalArgumentException(String.format("code(%d) 必须非0", code));
        }else{
            Rsp<T> result = new Rsp();
            result.code = code;
            result.info = info;
            return result;
        }
    }

    public static <T,S> Rsp<T> error(Rsp<S> rsp){
        return error(rsp,null);
    }
    public static <T,S> Rsp<T> error(Rsp<S> rsp,T data){
        if(rsp == null) return null;
        Rsp<T> newRsp = new Rsp<>();
        newRsp.setCode(rsp.getCode());
        newRsp.setInfo(rsp.getInfo());
        newRsp.setMark(rsp.getMark());
        newRsp.setValues(rsp.getValues());
        newRsp.setData(data);
        return newRsp;
    }


    public static <T> Rsp<T> error(RspCodeInfo rspCodeInfo){
        Rsp<T> rsp = error(rspCodeInfo.getInfo(), CODE_ERROR_COMMON);
        rsp.setMark(rspCodeInfo.getMark());
        return rsp;
    }

    public static <T> Rsp<T> error(RspCodeInfo rspCodeInfo,String info){
        Rsp<T> rsp = error(info, CODE_ERROR_COMMON);
        rsp.setMark(rspCodeInfo.getMark());
        return rsp;
    }

    public static <T> Rsp<T> error(String mark,String info){
        Rsp<T> rsp = error(info, CODE_ERROR_COMMON);
        rsp.setMark(mark);
        return rsp;
    }

    /**
     * 判断请求结果是不是成功
     * 用success代替
     * @return 请求结果
     */
    @Deprecated
    public Boolean isSuccess(){
        return null!=code && code.equals(CODE_SUCCESS);
    }


    public Boolean success(){
        return null!=code && code.equals(CODE_SUCCESS);
    }


    /**
     * 判断数据是否为空
     * @return
     */
    public Boolean hasData(){
        if(!success()) return false;
        if(data == null) return false;
        if(data instanceof Collection){
            return !((Collection<?>) data).isEmpty();
        }else if (data instanceof CharSequence){
            return StrUtil.isNotBlank((CharSequence)data);
        }else if (data instanceof Map){
            return! ((Map<?, ?>) data).isEmpty();
        }
        return true;
    }

    public T carefulGetData() throws Exception {
        try{
            return tryGetData();
        }catch (RspException ex){
            throw new Exception(ex);
        }
    }

    /**
     * 尝试获取数据，如果不成功，则抛出Exception异常
     * @return
     * @throws Exception
     */
    public T tryGetData() throws RspException{
        if(success()){
            if(getData()!=null) return getData();
            else{
                throw new RspException(RC.DATA_IS_NULL);
            }
        }else{
            RspCodeInfo rspCodeInfo = new RspCodeInfo();
            rspCodeInfo.setInfo(getInfo());
            rspCodeInfo.setMark(getMark());
            RspException rspException = new RspException(rspCodeInfo);
            rspException.setCode(getCode()==null?CODE_ERROR_COMMON:getCode());
            throw rspException;
        }
    }

    public Rsp<T> putValue(String key,Object value){
        if(values==null){
            values = new HashMap<>();
        }
        values.put(key,value);
        return this;
    }

    public Object getValue(String key){
        if(values==null) return null;
        if(!values.containsKey(key)) return null;
        return values.get(key);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Map<String, Object> getValues() {
        return values;
    }

    public void setValues(Map<String, Object> values) {
        this.values = values;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }
}
