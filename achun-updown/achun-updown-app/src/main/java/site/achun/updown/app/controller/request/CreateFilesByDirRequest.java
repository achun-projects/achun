package site.achun.updown.app.controller.request;

import lombok.Data;

import java.io.Serializable;

/**
 * Description
 *
 * @Author: Heiffeng
 * @Date: 2022/3/18 23:54
 */
@Data
public class CreateFilesByDirRequest implements Serializable {
    private static final long serialVersionUID = -6970647420268141959L;

    private String dir;
    private String groupCode;
}
