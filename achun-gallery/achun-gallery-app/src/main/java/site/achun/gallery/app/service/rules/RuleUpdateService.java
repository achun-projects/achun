package site.achun.gallery.app.service.rules;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import site.achun.gallery.app.generator.domain.QueryRule;
import site.achun.gallery.app.generator.service.QueryRuleService;
import site.achun.gallery.client.module.rules.beans.BaseRule;
import site.achun.gallery.client.module.rules.requset.CreateRule;
import site.achun.gallery.client.module.rules.requset.UpdateRule;
import site.achun.gallery.client.module.rules.response.RuleResponse;
import site.achun.support.api.exception.RspException;
import site.achun.support.api.response.RC;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class RuleUpdateService {

    private final QueryRuleService queryRuleService;

    public RuleResponse createRule(CreateRule req){
        QueryRule rule = BeanUtil.toBean(req,QueryRule.class);
        rule.setCtime(LocalDateTime.now());
        rule.setUtime(LocalDateTime.now());
        queryRuleService.save(rule);
        // TODO 需要添加到Redis中
        return BeanUtil.toBean(rule,RuleResponse.class);
    }

    public RuleResponse updateRule(UpdateRule req){
        QueryRule existRule = queryRuleService.queryBy(req.getRuleCode(),req.getUserCode());
        if(existRule == null){
            throw new RspException(RC.DATA_IS_NULL);
        }
        if(StrUtil.isNotEmpty(req.getName())) existRule.setName(req.getName());
        if(StrUtil.isNotEmpty(req.getDescription())) existRule.setDescription(req.getDescription());
        if(req.getRules()!=null){
            existRule.setRules(req.getRules());
        }
        queryRuleService.updateById(existRule);
        // TODO 需要添加到Redis中
        return BeanUtil.toBean(existRule,RuleResponse.class);
    }




}
