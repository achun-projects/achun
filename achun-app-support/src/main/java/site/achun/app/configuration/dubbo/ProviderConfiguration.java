package site.achun.app.configuration.dubbo;

import org.apache.dubbo.config.ProviderConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: SunAo
 * @Date: 2020/11/6 15:13
 * @Description:
 */
@Configuration
public class ProviderConfiguration {

    @Bean
    public ProviderConfig providerConfig() {
        ProviderConfig providerConfig = new ProviderConfig();
        providerConfig.setTimeout(1000);
        providerConfig.setValidation("true");
        return providerConfig;
    }

}
