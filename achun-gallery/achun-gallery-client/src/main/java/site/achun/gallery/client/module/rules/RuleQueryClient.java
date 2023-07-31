package site.achun.gallery.client.module.rules;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import site.achun.file.client.configuration.FileFeignConfiguration;
import site.achun.file.client.module.file.response.MediaFileResponse;
import site.achun.gallery.client.module.pictures.response.PicturesBasicInfo;
import site.achun.gallery.client.module.rules.requset.QueryFileByRuleCode;
import site.achun.support.api.response.Rsp;

import java.util.List;

@FeignClient(name = "achun-gallery-app", contextId = "RuleQueryClient",configuration = {FileFeignConfiguration.class})
public interface RuleQueryClient {
    @Operation(summary = "根据规则编码查询一组文件")
    @PostMapping("/gallery/rules/query-files-by-rule-code")
    Rsp<List<MediaFileResponse>> queryFilesByRuleCode(@RequestBody QueryFileByRuleCode query);

}
