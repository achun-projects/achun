package site.achun.gallery.client.module.fileset.request;

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
public class QueryFileSet implements Serializable {
    private String setCode;
    private String userCode;
    private String likeName;
}
