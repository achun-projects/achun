package site.achun.updown.app.runner;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSONObject;
import io.lettuce.core.RedisCommandTimeoutException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import site.achun.file.client.module.download.request.Task;
import site.achun.updown.app.service.module.download.DownloadTaskService;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 异步文件下载启动器
 *
 * @Author: Heiffeng
 * @Date: 2021/11/30 1:11
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class DownloadTaskRunner implements CommandLineRunner {

    @Value("${download.task.open}")
    private Boolean open;

    private final DownloadTaskService downloadTaskService;

    private final StringRedisTemplate redisTemplate;

    public static Boolean WAITING = false;

    private final static ThreadPoolExecutor POOL = new ThreadPoolExecutor(
            1,
            2,
            30,
            TimeUnit.SECONDS,
            new SynchronousQueue<>(),
            (r, executor) ->{
                try {
                    executor.getQueue().put(r);
                } catch (InterruptedException e) {e.printStackTrace();}
            }
    );

    @Override
    public void run(String... args) throws Exception {
        // 异步下载开关状态判断
        log.info("异步下载开关状态为：{}",open);
        if(!open){
            log.info("异步下载状态为关闭，不执行异步下载任务。");
            return;
        }
        // 启动下载线程
        Thread asynThread = new Thread(() -> {
            while(true) {
                try {
                    if(WAITING){
                        waiting(5);
                    }
                    // 从redis拉取队列数据
                    String value = redisTemplate.opsForList().leftPop(Task.KEY, 10, TimeUnit.SECONDS);
                    if (StrUtil.isEmpty(value)) {
                        continue;
                    }
                    Task task = JSONObject.parseObject(value, Task.class);
                    if (task == null) {
                        log.info("无法转换，value:{}", value);
                        continue;
                    }
                    POOL.execute(()->{
                        downloadTaskService.tryDownload(task);
                    });
                } catch (RedisCommandTimeoutException ex) {
                    log.info("Redis等待超时……，进行下一次等待");
                } catch (RedisConnectionFailureException ex){
                    log.info("Redis链接失败……，1分钟后尝试重新链接");
                    waiting(1);
                } catch (Exception ex){
                    log.error("下载任务出现异常，1分钟后尝试下一次下载",ex);
                    waiting(1);
                    ex.printStackTrace();
                }
            }
        });
        asynThread.start();
    }


    private void waiting(int minutes){
        try { Thread.sleep(1000 * 60 * minutes);
        } catch (InterruptedException e) {e.printStackTrace();}
    }

}
