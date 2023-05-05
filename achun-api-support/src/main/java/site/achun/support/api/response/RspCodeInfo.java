package site.achun.support.api.response;

import java.io.Serializable;

/**
 * Description
 *
 * @Author: Heiffeng
 * @Date: 2021/11/30 18:46
 */
public class RspCodeInfo implements Serializable {

    private static final long serialVersionUID = -3345131442603723043L;

    private String mark;
    private String info;

    public RspCodeInfo() {
    }

    public RspCodeInfo(String mark, String info) {
        this.mark = mark;
        this.info = info;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
