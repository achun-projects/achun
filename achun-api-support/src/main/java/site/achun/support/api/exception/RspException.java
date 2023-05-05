package site.achun.support.api.exception;


import site.achun.support.api.response.Rsp;
import site.achun.support.api.response.RspCodeInfo;

/**
 *
 * @Author: SunAo
 * @Date: 2020/12/14 19:41
 */
public class RspException extends RuntimeException {

    private Integer code;
    private String info;
    private String mark;


    public RspException(String info) {
        this.code = Rsp.CODE_ERROR_COMMON;
        this.info = info;
        this.mark = "";
    }

    public RspException(Integer code, String info) {
        this.code = code;
        this.info = info;
        this.mark = "";
    }

    public RspException(RspCodeInfo rspCodeInfo) {
        this.code = Rsp.CODE_ERROR_COMMON;
        this.info = rspCodeInfo.getInfo();
        this.mark = rspCodeInfo.getMark();
    }

    public RspException(RspCodeInfo rspCodeInfo,String messageTemplate,String... args) {
        this.code = Rsp.CODE_ERROR_COMMON;
        this.info = String.format(messageTemplate,args);
        this.mark = rspCodeInfo.getMark();
    }

    @Override
    public String getMessage() {
        return String.format("错误码：%d,错误编码:%s,错误信息：%s",code,mark,info);
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

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }
}
