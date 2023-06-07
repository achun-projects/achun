package site.achun.gallery.client.module.group.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Description
 *
 * @Author: Heiffeng
 * @Date: 2022/1/28 11:12
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateGalleryGroup implements Serializable {
    private static final long serialVersionUID = 5277246446647801299L;

    /**
     * 分组编码
     */
    private String groupCode;

    /**
     * 父级分组编码，为空或等于自己，则是顶级菜单
     */
    private String parentGroupCode;

    /**
     * 分组名
     */
    private String name;

    /**
     * 分组描述
     */
    private String description;

    /**
     * 用户编号
     */
    private String userCode;

    /**
     * 类型，1-相册，2-画板
     */
    private Integer type;
}
