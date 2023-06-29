package site.achun.video.client.module.group.response;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 分组视图
 * <p>
 * Author: Heiffeng
 * Date: 2023/3/8
 */
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class GroupViewResponse implements Serializable {

    /**
     * 分组编码
     */
    private String groupCode;

    /**
     * 父级分组编码，为空或等于自己，则是顶级菜单
     */
    private String parentGroupCode;

    private String parentGroupName;

    /**
     * 子元素数量
     */
    private Integer childNum;

    /**
     * 关联记录的数量
     */
    private Integer recordNum;

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

    /**
     * 创建时间
     */
    private LocalDateTime ctime;

    /**
     * 修改时间
     */
    private LocalDateTime utime;

    /**
     * 记录更新时间
     */
    private LocalDateTime recordUtime;
}
