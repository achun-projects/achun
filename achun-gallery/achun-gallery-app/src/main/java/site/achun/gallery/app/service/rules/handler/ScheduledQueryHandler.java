package site.achun.gallery.app.service.rules.handler;

import cn.hutool.core.util.StrUtil;
import cn.hutool.cron.pattern.CronPatternUtil;
import com.alibaba.fastjson2.JSONArray;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import site.achun.gallery.app.service.rules.QueryHandler;
import site.achun.gallery.app.service.rules.RuleQueryService;
import site.achun.gallery.client.module.rules.beans.RuleType;
import site.achun.gallery.client.module.rules.beans.Scheduled;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ScheduledQueryHandler implements QueryHandler {

    @Lazy
    @Autowired
    private RuleQueryService ruleQueryService;

    @Override
    public boolean match(RuleType type) {
        return RuleType.SCHEDULED.equals(type);
    }

    @Override
    public List<String> query(String rule) {
        List<Scheduled> scheduledList = JSONArray.parseArray(rule, Scheduled.class);
        LocalDateTime now = LocalDateTime.now();
        Date dateTime = Date.from(now.atZone(ZoneId.systemDefault()).toInstant());
        Date dateTimePlus = Date.from(now.plusMinutes(1).atZone(ZoneId.systemDefault()).toInstant());
        Scheduled scheduled = scheduledList.stream()
                .sorted((a, b) -> a.getOrder() >= b.getOrder() ? 1 : -1)
                .filter(s -> {
                    if (StrUtil.isEmpty(s.getCron())) {
                        // 定时为空，则为全匹配
                        return true;
                    }
                    List<Date> cronMatchList = CronPatternUtil.matchedDates(s.getCron(), dateTime, dateTimePlus, 10, true);
                    if (cronMatchList.size() > 0) {
                        // 时间和cron匹配到了。
                        return true;
                    }
                    return false;
                }).findFirst().get();
        return ruleQueryService.queryFileCodesByRuleCode(scheduled.getRuleCode());
    }
}
