package site.achun.file.util;

import cn.hutool.crypto.symmetric.AES;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

public class FileAuthUtil {
    private final static String KEY = "THISISFILEAUTHUT";
    private final static Integer TIMEOUT = 1;
    private final static TemporalUnit TIMEOUT_UNIT = ChronoUnit.HOURS;

    private final static cn.hutool.crypto.symmetric.AES AES = new AES(KEY.getBytes());

    public static String generatorToken(String url){
        LocalDateTime expireTime = LocalDateTime.now().plus(TIMEOUT, TIMEOUT_UNIT);
        long milli = expireTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        String shortUrl = substringUrl(url);
        String dataStr = milli/1000 + shortUrl;
        return AES.encryptHex(dataStr);
    }

    public static boolean checkToken(String token,String url){
        String dataStr = AES.decryptStr(token);
        String milli = dataStr.substring(0,10);
        String decodeUrl = dataStr.substring(10);
        long nowMilli = LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        long expireMilli = Long.parseLong(milli) * 1000;
        if(nowMilli > expireMilli){
            // 如果现在的时间大于失效时间，则token失效
            throw new RuntimeException("token超时失效");
        }
        if(!substringUrl(url).equals(decodeUrl)){
            // 资源url与待检测的不同，token无效
            throw new RuntimeException("请求资源不正确,token无效");
        }
        return true;
    }

    private static String substringUrl(String url){
        int lastIndexOf = url.lastIndexOf(".");
        int start = (lastIndexOf - 21) < 0 ? 0 : (lastIndexOf - 21);
        return url.substring(start, lastIndexOf);
    }
}
