package site.achun.gallery.app.controller.rules;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.URLUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import site.achun.file.client.module.file.MediaFileQueryClient;
import site.achun.file.client.module.file.request.QueryByFileCode;
import site.achun.file.client.module.file.response.MediaFileResponse;
import site.achun.gallery.app.service.list.ListRandomQueryService;
import site.achun.gallery.app.service.rules.RandomRules;
import site.achun.gallery.app.service.rules.Rule;
import site.achun.gallery.app.service.rules.RuleQueryService;
import site.achun.gallery.app.service.rules.RuleUtil;
import site.achun.gallery.app.utils.UserInfo;
import site.achun.gallery.client.module.rules.RuleQueryClient;
import site.achun.gallery.client.module.rules.requset.QueryFileByRuleCode;
import site.achun.support.api.response.Rsp;

import java.io.IOException;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Tag(name = "规则查询")
@RestController
@RequiredArgsConstructor
public class RuleQueryController implements RuleQueryClient {

    private final ListRandomQueryService listRandomQueryService;
    private final RuleQueryService ruleQueryService;
    private final MediaFileQueryClient mediaFileQueryClient;

    @Override
    public Rsp<List<MediaFileResponse>> queryFilesByRuleCode(QueryFileByRuleCode query) {
        return Rsp.success(ruleQueryService.queryFilesByRuleCode(query.getRuleCode()));
    }

    @Operation(summary = "根据规则编码随机获取图片")
    @GetMapping("/gallery/random-get/{ruleCode}")
    public void randomByRuleCode(@PathVariable("ruleCode") String ruleCode,
                                 HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<MediaFileResponse> list = ruleQueryService.queryFilesByRuleCode(ruleCode);
        if(CollUtil.isEmpty(list)){
            log.info("根据规则编码随机获取图片，列表无数据，ruleCode:{}",ruleCode);
            return;
        }
        MediaFileResponse fileResponse = RandomUtil.randomEle(list);
        String url = fileResponse.getUrl();
        // 重定向到url
//
//        // 设置字符编码为 UTF-8
//        response.setCharacterEncoding("UTF-8");
//        // 设置响应头的 Content-Type，包括字符编码
//        response.setContentType("text/plain; charset=UTF-8");

        response.sendRedirect(URLEncoder.encode(url, "UTF-8"));


    }

}
