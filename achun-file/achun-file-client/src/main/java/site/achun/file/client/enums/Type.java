package site.achun.file.client.enums;

import site.achun.support.api.utils.EnumUtil;

import java.util.Optional;

/**
 * Description
 *
 * @Author: SunAo
 * @Date: 2021/7/26 13:40
 */
public enum Type {
    OTHER(0,"其他","")
    ,IMAGE(1,"图片","jpg,jpeg,gif,png")
    ,VIDEO(2,"视频","mp4,flv,mov")
    ,AUDIO(3,"音频","mp3");
    private Integer code;
    private String desc;
    private String suffix;

    Type(Integer code, String desc, String suffix) {
        this.code = code;
        this.desc = desc;
        this.suffix = suffix;
    }

    public static Optional<Type> parse(Integer code){
        return EnumUtil.parse(Type.values(),Type::getCode,code);
    }

    public static Type parse(String suffix){
        for (Type value : Type.values()) {
            if(value.getSuffix().indexOf(suffix.toLowerCase())>-1){
                return value;
            }
        }
        return OTHER;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer status) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

}
