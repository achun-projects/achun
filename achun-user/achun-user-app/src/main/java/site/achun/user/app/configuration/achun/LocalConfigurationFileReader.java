package site.achun.user.app.configuration.achun;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.util.FileUtil;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import site.achun.user.app.utils.RsaUtil;

import java.io.File;

@Slf4j
@Component
public class LocalConfigurationFileReader implements CommandLineRunner {


    private final static String CONFIG_FILE_PATH = "/root/.achun/config.json";
    @Override
    public void run(String... args) throws Exception {
        RsaUtil.init(AchunConfig.config.getLoginRsaPublicKey(),AchunConfig.config.getLoginRsaPrivateKey());
        File file = new File(CONFIG_FILE_PATH);
        if(!file.exists()){
            log.info("自定义配置文件不存在");
            return;
        }
        String fileContent = FileUtil.readAsString(file);
        if(StrUtil.isEmpty(fileContent)){
            log.info("自定义配置文件存在,但内容为空");
            return;
        }
        try{
            AchunConfig achunConfig = JSONObject.parseObject(fileContent, AchunConfig.class);
            AchunConfig.config = achunConfig;
            RsaUtil.init(AchunConfig.config.getLoginRsaPublicKey(),AchunConfig.config.getLoginRsaPrivateKey());
            log.info("自定义配置文件加载成功.");
        }catch (Exception ex){
            log.error("自定义配置文件解析失败");
        }
    }


}
