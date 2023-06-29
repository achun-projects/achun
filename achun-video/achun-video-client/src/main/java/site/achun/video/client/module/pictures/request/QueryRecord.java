package site.achun.video.client.module.pictures.request;

import lombok.*;
import site.achun.support.api.request.ReqPage;

import java.io.Serializable;
import java.util.Set;

/**
 * Description
 *
 * @Author: Heiffeng
 * @Date: 2022/1/16 16:26
 */
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QueryRecord implements Serializable {
    private static final long serialVersionUID = -708455120127070463L;
    // 分页
    private ReqPage reqPage;
    // 相册或画板编码
    private String code;

    // 相册或画板编码
    private Set<String> codes;

    // 用户编码
    private String userCode;

    // 最大wh
    private Integer maxWH;

    // 最小wh
    private Integer minWH;

    // 排除的画板编码
    private Set<String> excludeBoardCodes;

    // TODO 时间范围查询
    // 1. 创建时间，2，修改时间，3.时间轴时间
    private Integer timeType;

    private String beginTime;

    private String endTime;

    private String search;

    // 喜欢的图片置顶显示
    private Boolean likeTop = true;

    // 只查询喜欢的图片
    private Boolean justQueryLike = false;

    // 排除喜欢的图片
    private Boolean excludeLike = false;
}
