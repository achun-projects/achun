package site.achun.gallery.client.module.rules.beans;

import java.io.Serializable;

public class BaseRule implements Serializable {
    private Integer weight;
    private String type;
    private Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
