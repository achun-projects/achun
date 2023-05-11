package site.achun.support.api.exception;


import site.achun.support.api.response.RspCodeInfo;

/**
 * Description
 *
 * @Author: Heiffeng
 * @Date: 2022/3/19 0:18
 */
public class LogicException extends RuntimeException{

    private RspCodeInfo rspCodeInfo;

    public LogicException(RspCodeInfo rspCodeInfo) {
        this.rspCodeInfo = rspCodeInfo;
    }

    public LogicException(RspCodeInfo rspCodeInfo,String info) {
        this.rspCodeInfo = rspCodeInfo;
        this.rspCodeInfo.setInfo(info);
    }

    public LogicException(String mark,String info) {
        this.rspCodeInfo = new RspCodeInfo();
        this.rspCodeInfo.setMark(mark);
        this.rspCodeInfo.setInfo(info);
    }

    public RspCodeInfo getRspCodeInfo() {
        return rspCodeInfo;
    }

    public void setRspCodeInfo(RspCodeInfo rspCodeInfo) {
        this.rspCodeInfo = rspCodeInfo;
    }
}
