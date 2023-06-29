package site.achun.video.app.service.rules;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * Description
 *
 * @Author: Heiffeng
 * @Date: 2022/7/5 14:06
 **/
@Getter
@AllArgsConstructor
public enum RandomRules {
    TERMINAL("0001",Arrays.asList(
            new Rule("深夜",2,Arrays.asList("* * 0-5 * * ? *"), Set.of("8553f1d865ff4706bbe5ac6953367a57")),
            new Rule("工作日",1,Arrays.asList("* * 8-19 * * 1,2,3,4,5 *"),Set.of("739e23ea8e1846998c61b391d485a5ae")), // 周一到周五 8点到19点
            new Rule("默认规则",3,null,Set.of("dc9b249c862344b2b9966326ca8f5a25"))
    ));

    private String ruleCode;
    private List<Rule> rules;

    public static RandomRules parse(String ruleCode){
        for (RandomRules rule : RandomRules.values()) {
            if(rule.getRuleCode().equals(ruleCode)){
                return rule;
            }
        }
        return null;
    }

}
