package site.achun.gallery.app.generator.service;

import site.achun.gallery.app.generator.domain.QueryRule;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author Administrator
* @description 针对表【query_rule】的数据库操作Service
* @createDate 2023-11-16 18:03:36
*/
public interface QueryRuleService extends IService<QueryRule> {

    default QueryRule queryBy(String ruleCode){
        return this.lambdaQuery()
                .eq(QueryRule::getRuleCode,ruleCode)
                .one();
    }

    default QueryRule queryBy(String ruleCode,String userCode){
        return this.lambdaQuery()
                .eq(QueryRule::getRuleCode,ruleCode)
                .eq(QueryRule::getUserCode,userCode)
                .one();
    }

}
