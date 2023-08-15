package site.achun.audio.app.controller.audio_file;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import site.achun.audio.client.module.audio_file.AudioFileQueryClient;

@Slf4j
@Tag(name = "音频文件查询")
@RestController
@RequiredArgsConstructor
public class AudioFileQueryController implements AudioFileQueryClient {

}
