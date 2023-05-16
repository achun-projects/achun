package site.achun.file.client.enums;

import site.achun.support.api.utils.EnumUtil;

import java.util.Optional;

/**
 * Description
 *
 * @Author: SunAo
 * @Date: 2021/7/26 13:32
 */
public enum Hidden {

    YES(1,"隐藏文件"),
    NO(2,"非隐藏文件");

    private Integer status;
    private String desc;

    Hidden(Integer status, String desc) {
        this.status = status;
        this.desc = desc;
    }

    public static Optional<Hidden> parse(Integer status){
        return EnumUtil.parse(Hidden.values(),Hidden::getStatus,status);
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
