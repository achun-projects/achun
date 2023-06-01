package site.achun.gallery.app.service.rules;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.cron.pattern.CronPatternUtil;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class RandomRulesTest {

    @Test
    public void 测试(){
        System.out.println(parseNowBoardCodes(RandomRules.TERMINAL).getName());
    }


    private Rule parseNowBoardCodes(RandomRules rules){
        Date now = new Date();
        Date nowPlus = Date.from(LocalDateTime.now().plusMinutes(1).atZone(ZoneId.systemDefault()).toInstant());
        Rule ruleResult = rules.getRules().stream().sorted()
                .filter(rule -> {
                    if(CollUtil.isEmpty(rule.getCron())){
                        return true;
                    }
                    for (String cron : rule.getCron()) {
                        List<Date> cronMatchList = CronPatternUtil.matchedDates(cron, now, nowPlus, 10, true);
                        if(cronMatchList.size() > 0 ){
                            return true;
                        }
                    }
                    return false;
                }).findFirst().get();
        return ruleResult;
    }
}
