package site.achun.gallery.client.module.group.view;

import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * Description
 *
 * @Author: Heiffeng
 * @Date: 2022/1/16 12:43
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CascaderView implements Serializable {
    private static final long serialVersionUID = -2879265455408268517L;

    // 级联器取值
    private String value;
    // 级联器显示值
    private String label;
    // 子元素
    private List<CascaderView> children;
}
