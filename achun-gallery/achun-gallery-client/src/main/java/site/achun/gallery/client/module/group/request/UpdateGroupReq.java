package site.achun.gallery.client.module.group.request;

import lombok.*;

import java.io.Serializable;

/**
 * 更新分组
 * <p>
 * Author: Heiffeng
 * Date: 2023/3/9
 */
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UpdateGroupReq implements Serializable {


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
