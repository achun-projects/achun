package site.achun.gallery.client.module.fileset.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Collection;

/**
 * Description
 *
 * @Author: Heiffeng
 * @Date: 2022/4/25 15:27
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateSetCode implements Serializable {

    private String setCode;
    private String userCode;
    private String name;
    private Collection<String> tags;
    private Collection<String> fileCodes;

}
