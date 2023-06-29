package site.achun.video.app.controller.settings;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import site.achun.support.api.response.Rsp;

/**
 * Description
 *
 * @Author: Heiffeng
 * @Date: 2022/5/16 10:34
 */
@Tag(name = "设置模块")
@RestController
@AllArgsConstructor
public class SettingController {

    private final static String DEFAULT_USER_CODE = "1";

    @Operation(summary = "NSFW开关")
    @GetMapping("/video/settings/set-nsfw-switch")
    public Rsp<Boolean> nsfwSwitch(@RequestParam("show") Boolean show){
//        platformMapConsumer.showNsfwContent(show);
        return Rsp.success(true);
    }

    @Operation(summary = "获取NSFW开关")
    @GetMapping("/video/settings/get-nsfw-switch")
    public Rsp<Boolean> getNsfwSwitch(){
        return Rsp.success(true);
    }

}
