package site.achun.gallery.app.controller.rules;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import site.achun.gallery.app.service.rules.RuleUpdateService;
import site.achun.gallery.client.module.rules.requset.CreateRule;
import site.achun.gallery.client.module.rules.requset.UpdateRule;
import site.achun.gallery.client.module.rules.response.RuleResponse;
import site.achun.support.api.response.Rsp;

@Slf4j
@Tag(name = "规则更新")
@RestController
@RequiredArgsConstructor
public class RuleUpdateController {

    private final RuleUpdateService ruleUpdateService;

    @Operation(summary = "创建规则")
    @PostMapping("/gallery/rules/create-rule")
    public Rsp<RuleResponse> createRule(@RequestBody CreateRule req){
        return Rsp.success(ruleUpdateService.createRule(req));
    }

    @Operation(summary = "更新规则")
    @PostMapping("/gallery/rules/update-rule")
    public Rsp<RuleResponse> updateRule(@RequestBody UpdateRule req){
        return Rsp.success(ruleUpdateService.updateRule(req));
    }
}
