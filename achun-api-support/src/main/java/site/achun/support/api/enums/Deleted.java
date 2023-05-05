package site.achun.support.api.enums;


import site.achun.support.api.utils.EnumUtil;

import java.util.Optional;

/**
 * Description
 *
 * @Author: SunAo
 * @Date: 2021/7/26 11:59
 */
public enum Deleted {

    YES(1,"已删除"),
    NO(2,"未删除");

    private Integer status;
    private String value;

    Deleted(Integer status, String value) {
        this.status = status;
        this.value = value;
    }

    public static Optional<Deleted> parse(Integer status){
        return EnumUtil.parse(Deleted.values(),Deleted::getStatus,status);
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
