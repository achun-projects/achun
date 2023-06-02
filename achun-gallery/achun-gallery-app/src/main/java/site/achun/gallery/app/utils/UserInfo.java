package site.achun.gallery.app.utils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Enumeration;

public class UserInfo {


    public static HttpServletRequest getCurrentRequest() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes != null) {
            return requestAttributes.getRequest();
        }
        return null;
    }

    public static String getUserCode(){
        HttpServletRequest request = getCurrentRequest();

        String name = null;
        Enumeration<String> names = request.getHeaderNames();
        while((name = names.nextElement())!=null){
            System.out.println("Name:"+name+",Value:"+request.getHeader(name));
        }

        return request.getHeader("user-code");
    }



}
