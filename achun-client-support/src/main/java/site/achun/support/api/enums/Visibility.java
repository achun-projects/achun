package site.achun.support.api.enums;

public enum Visibility {

    ALL(1),
    LOGIN(2),
    HOST_LOGIN(3),
    PRIVATE(4);

    private Integer level;

    Visibility(Integer level) {
        this.level = level;
    }

    public Integer getLevel() {
        return level;
    }
}
