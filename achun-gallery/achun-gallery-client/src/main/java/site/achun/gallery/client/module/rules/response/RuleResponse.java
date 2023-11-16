package site.achun.gallery.client.module.rules.response;

import lombok.Data;
import site.achun.gallery.client.module.rules.beans.BaseRule;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class RuleResponse implements Serializable {

    /**
     * ID
     */
    private Integer id;

    /**
     * 规则编码
     */
    private String ruleCode;

    /**
     * 用户编码
     */
    private String userCode;

    /**
     * 规则名称
     */
    private String name;

    /**
     * 详细描述
     */
    private String description;

    /**
     * 规则内容
     */
    private List<BaseRule> rules;

    /**
     * 创建时间
     */
    private LocalDateTime ctime;

    /**
     * 编辑时间
     */
    private LocalDateTime utime;
}
