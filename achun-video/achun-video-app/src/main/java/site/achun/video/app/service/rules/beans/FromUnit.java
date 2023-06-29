package site.achun.video.app.service.rules.beans;

import lombok.Data;

import java.util.List;

@Data
public class FromUnit {
    private List<String> albumCodes;
    private Integer maxCount;
}
