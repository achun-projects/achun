package site.achun.updown.app.controller.request;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Collection;

/**
 * Description
 *
 * @Author: Heiffeng
 * @Date: 2022/3/20 10:44
 */
@Data
@ToString
public class CreateFilesRequest extends CreateFilesByDirRequest implements Serializable {
    private static final long serialVersionUID = -7514534129131826258L;

    private Collection<String> files;
}
