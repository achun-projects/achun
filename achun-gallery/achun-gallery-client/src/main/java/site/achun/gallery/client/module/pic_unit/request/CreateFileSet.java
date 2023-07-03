package site.achun.gallery.client.module.pic_unit.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateFileSet implements Serializable {

    /**
     * 用户code
     */
    private String userCode;

    /**
     * 分组名称
     */
    private String name;
    private String desc;

    private String setCode;

}
