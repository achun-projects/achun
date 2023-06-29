package site.achun.video.app.service.execute.record;

import lombok.Data;

import java.io.Serializable;

/**
 * Description
 *
 * @Author: Heiffeng
 * @Date: 2022/10/17 18:20
 */
@Data
public class ObjectNum implements Serializable {
    private String code;
    private Integer num;
}
