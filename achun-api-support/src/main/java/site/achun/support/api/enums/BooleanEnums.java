package site.achun.support.api.enums;

/**
 * Description
 *
 * @Author: SunAo
 * @Date: 2021/3/18 17:40
 */
public enum BooleanEnums {

    TRUE(Boolean.TRUE,"yes"),
    FALSE(Boolean.FALSE,"no");

    private Boolean bool;
    private String value;

    BooleanEnums(Boolean bool, String value) {
        this.bool = bool;
        this.value = value;
    }

    public Boolean getBool() {
        return bool;
    }

    public void setBool(Boolean bool) {
        this.bool = bool;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
