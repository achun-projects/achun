package site.achun.video.app.service.rules;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.cron.pattern.CronPatternUtil;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

/**
 * Description
 *
 * @Author: Heiffeng
 * @Date: 2022/7/26 18:24
 */
public class RuleUtil {

    public static void main(String[] args) {
        RandomRules rules = RandomRules.TERMINAL;
        LocalDateTime time = LocalDateTime.of(2022,10,1,23,30,11);
        System.out.println(getRuleBy(rules, time).getName());
    }

    public static Rule getRuleBy(RandomRules rules,LocalDateTime localDateTime){
        Date dateTime = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        Date dateTimePlus = Date.from(localDateTime.plusMinutes(1).atZone(ZoneId.systemDefault()).toInstant());
        Rule ruleResult = rules.getRules().stream().sorted()
                .filter(rule -> {
                    if(CollUtil.isEmpty(rule.getCron())){
                        return true;
                    }
                    for (String cron : rule.getCron()) {
                        List<Date> cronMatchList = CronPatternUtil.matchedDates(cron, dateTime, dateTimePlus, 10, true);
                        if(cronMatchList.size() > 0 ){
                            return true;
                        }
                    }
                    return false;
                }).findFirst().get();
        return ruleResult;
    }
}
