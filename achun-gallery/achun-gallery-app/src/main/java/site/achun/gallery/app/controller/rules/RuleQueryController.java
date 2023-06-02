package site.achun.gallery.app.controller.rules;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import site.achun.gallery.app.service.list.ListRandomQueryService;
import site.achun.gallery.app.service.pictures.PicturesQueryService;
import site.achun.gallery.app.service.rules.RandomRules;
import site.achun.gallery.app.service.rules.Rule;
import site.achun.gallery.app.service.rules.RuleQueryService;
import site.achun.gallery.app.service.rules.RuleUtil;
import site.achun.gallery.app.utils.UserInfo;
import site.achun.gallery.client.module.pictures.response.PicturesBasicInfo;
import site.achun.gallery.client.module.rules.RuleQueryClient;
import site.achun.gallery.client.module.rules.requset.QueryFileByRuleCode;
import site.achun.support.api.response.Rsp;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Tag(name = "规则查询")
@RestController
@RequiredArgsConstructor
public class RuleQueryController implements RuleQueryClient {

    private final ListRandomQueryService listRandomQueryService;
    private final PicturesQueryService picturesQueryService;
    private final RuleQueryService ruleQueryService;

    @Override
    public Rsp<List<PicturesBasicInfo>> queryFilesByRuleCode(QueryFileByRuleCode query) {
        return Rsp.success(ruleQueryService.queryFilesByRuleCode(query.getRuleCode()));
    }

    @Operation(summary = "根据规则编码随机获取图片")
    @GetMapping("/gallery/random-get/{ruleCode}")
    public void randomByRuleCode(@PathVariable("ruleCode") String ruleCode,
                                 HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.info("userCode:{}", UserInfo.getUserCode());
        // TODO 验证用户权限
        RandomRules rules = RandomRules.parse(ruleCode);
        Rule rule = RuleUtil.getRuleBy(rules, LocalDateTime.now());
        log.info("匹配到rule:{}",rule.getName());
        String randomFileCode = listRandomQueryService.randomQuery(rule.getBoards());
        PicturesBasicInfo pic = picturesQueryService.queryBasicInfo(randomFileCode);
        String url = pic.getUrl();
        // 重定向到url
        response.sendRedirect(url);
    }

}
