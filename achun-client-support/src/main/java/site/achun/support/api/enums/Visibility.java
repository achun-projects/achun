package site.achun.support.api.enums;

public enum Visibility {

    ALL(1),
    HOST_LOGIN(2),
    LOGIN(3),
    PRIVATE(4);

    private Integer level;

    Visibility(Integer level) {
        this.level = level;
    }

    public Integer getLevel() {
        return level;
    }
}
