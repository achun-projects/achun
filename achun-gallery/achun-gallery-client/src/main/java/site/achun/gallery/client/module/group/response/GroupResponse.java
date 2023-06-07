package site.achun.gallery.client.module.group.response;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Description
 *
 * @Author: Heiffeng
 * @Date: 2022/1/14 22:14
 */
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class GroupResponse implements Serializable {

    /**
     * 主键
     */
    private Integer id;

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
     * 记录更新时间
     */
    private LocalDateTime recordUtime;

}
