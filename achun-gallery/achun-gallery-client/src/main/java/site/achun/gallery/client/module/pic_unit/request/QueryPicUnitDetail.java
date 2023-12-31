package site.achun.gallery.client.module.pic_unit.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Author: Heiffeng
 * Date: 2023/3/27
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QueryPicUnitDetail implements Serializable {
    private String picUnitCode;
    private String userCode;
    private String likeName;
}
