package site.achun.file.controller.download;

import com.alibaba.fastjson2.JSON;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RestController;
import site.achun.file.client.module.download.DownloadClient;
import site.achun.file.client.module.download.request.Task;
import site.achun.support.api.response.Rsp;

@Slf4j
@Tag(name = "文件下载服务")
@RequiredArgsConstructor
@RestController
public class DownloadController implements DownloadClient {

    private final StringRedisTemplate redisTemplate;

    @Override
    public Rsp<Boolean> newTask(Task task) {
        redisTemplate.opsForList().rightPush(Task.KEY, JSON.toJSONString(task));
        return Rsp.success(Boolean.TRUE);
    }
}
