package site.achun.gallery.app.service.rules.beans;

import lombok.Data;

import java.util.List;

@Data
public class FromLists{

    private String name;
    private List<String> values;
    private Long count = 1L;

}
