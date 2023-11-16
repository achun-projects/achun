package site.achun.gallery.client.module.rules.beans;

import java.io.Serializable;

public class BaseRule implements Serializable {
    private Integer weight;
    private String type;

    private String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
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
