package site.achun.gallery.client.module.pic_unit.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Collection;

/**
 * Author: Heiffeng
 * Date: 2023/3/27
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePicUnit implements Serializable {
    private String setCode;
    private String userCode;
    private String name;
    private Collection<String> tags;
}
