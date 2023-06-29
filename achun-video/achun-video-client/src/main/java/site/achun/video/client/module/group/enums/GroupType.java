package site.achun.video.client.module.group.enums;

import site.achun.support.api.utils.EnumUtil;

/**
 * Description
 *
 * @Author: Heiffeng
 * @Date: 2022/1/16 12:49
 **/
public enum GroupType {

    ALBUM(1,"相册"),
    BOARD(2,"画板");

    private Integer type;
    private String desc;

    GroupType(Integer type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public static GroupType parse(Integer type) {
        return EnumUtil.parse(GroupType.values(),GroupType::getType,type).orElse(null);
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
