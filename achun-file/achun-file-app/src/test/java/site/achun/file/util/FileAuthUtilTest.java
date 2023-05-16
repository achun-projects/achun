package site.achun.file.util;

import cn.hutool.crypto.symmetric.AES;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

public class FileAuthUtilTest {

    @Test
    public void testShortUrl(){
        String url = "239.jpg";
        String token = FileAuthUtil.generatorToken(url);
        boolean checkResult = FileAuthUtil.checkToken(token, url);
        System.out.println("url:"+url+" token:"+token+" checkResult:"+checkResult);
    }

    @Test
    public void testNormalUrl(){
        String url = "202305/132a0537253d6def458a01e6512c3387/132a0537253d6def458a01e6512c3387-90807148610101239.cover.jpg";
        String token = FileAuthUtil.generatorToken(url);
        boolean checkResult = FileAuthUtil.checkToken(token, url);
        System.out.println("url:"+url+" token:"+token+" checkResult:"+checkResult);
    }

    @Test
    public void testTimeout() throws InterruptedException {
        String url = "202305/132a0537253d6def458a01e6512c3387/132a0537253d6def458a01e6512c3387-90807148610101239.cover.jpg";
        // 5秒后失效
        LocalDateTime expireTime = LocalDateTime.now().plus(5, ChronoUnit.SECONDS);
        long milli = expireTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        String shortUrl = substringUrl(url);
        String dataStr = milli/1000 + shortUrl;
        String token = AES.encryptHex(dataStr);

        int loop = 7,index = 0;
        while (index < loop){
            index++;
            Thread.sleep(1000);
            System.out.println("url:"+url+" token:"+token+" checkResult:"+FileAuthUtil.checkToken(token, url));
        }

    }

    @Test
    public void testWrongUrl(){
        String url = "202305/132a0537253d6def458a01e6512c3387/132a0537253d6def458a01e6512c3387-90807148610101239.cover.jpg";
        String wrongUrl = "202305/132a0537253d6def458a01e6512c3387/132a0537253d6def458a01e6512c3387-90807148610101240.cover.jpg";
        String token = FileAuthUtil.generatorToken(url);
        boolean checkResult = FileAuthUtil.checkToken(token, wrongUrl);
        System.out.println("url:"+url+" token:"+token+" checkResult:"+checkResult);
    }

    private static String substringUrl(String url){
        int lastIndexOf = url.lastIndexOf(".");
        int start = (lastIndexOf - 21) < 0 ? 0 : (lastIndexOf - 21);
        return url.substring(start, lastIndexOf);
    }


    @Test
    public void generatorToken(){
        String url = "0101239.cover.jpg";
        String token = FileAuthUtil.generatorToken(url);
        System.out.println(token);
    }

    @Test
    public void getNowTime(){
        LocalDateTime expireTime = LocalDateTime.now().plus(3, ChronoUnit.HOURS);
        long milli = expireTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        System.out.println(milli/1000);
    }


    private final static String KEY = "FKXIAOJUDAYBYDAY";
    private final static cn.hutool.crypto.symmetric.AES AES = new AES(KEY.getBytes());

    @Test
    public void decryptStr(){
        String dataStr = AES.decryptStr("e70b2b5a39599a00a37d6b5aaa86c44530eba112d58fe28fc896272f3e9306c9");
        System.out.println(dataStr);
        String milli = dataStr.substring(0,10);
        String decodeUrl = dataStr.substring(10);
        System.out.println(milli+"::"+decodeUrl);
    }

}
