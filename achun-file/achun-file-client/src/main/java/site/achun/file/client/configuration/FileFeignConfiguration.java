package site.achun.file.client.configuration;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class FileFeignConfiguration implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate template) {
        // 从header获取X-token
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        String env = request.getHeader("env");//网关传过来的 token
        if (StringUtils.hasText(env)) {
            template.header("env", env);
        }
    }
}
