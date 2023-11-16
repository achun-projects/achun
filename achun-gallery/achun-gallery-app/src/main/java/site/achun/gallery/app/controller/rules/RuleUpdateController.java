package site.achun.gallery.app.controller.rules;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import site.achun.gallery.client.module.rules.requset.CreateRule;
import site.achun.gallery.client.module.rules.requset.UpdateRule;
import site.achun.gallery.client.module.rules.response.RuleResponse;
import site.achun.support.api.response.Rsp;

@Slf4j
@Tag(name = "规则更新")
@RestController
@RequiredArgsConstructor
public class RuleUpdateController {

    @Operation(summary = "根据规则编码查询一组文件")
    @PostMapping("/gallery/rules/create-rule")
    public Rsp<RuleResponse> createRule(@RequestBody CreateRule req){
        return null;
    }

    @Operation(summary = "根据规则编码查询一组文件")
    @PostMapping("/gallery/rules/update-rule")
    public Rsp<RuleResponse> updateRule(@RequestBody UpdateRule req){
        return null;
    }
}
