package site.achun.gallery.client.module.board.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

/**
 * Description
 *
 * @Author: Heiffeng
 * @Date: 2021/12/8 11:53
 */
@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BoardCreateRequest implements Serializable {
    private static final long serialVersionUID = -4854941044797372205L;

    /**
     * 画板名
     */
    @NotBlank
    private String name;

    /**
     * 画板描述
     */
    private String description;

    /**
     * 分组编码集合
     */
    private Set<String> groupCodes;

    /**
     * 用户编码
     */
    @NotBlank
    private String userCode;

    /**
     *
     */
    private String cover;

    /**
     * 是否为隐藏画板，1：隐藏，2：非隐藏
     */
    private Boolean hide;
}
