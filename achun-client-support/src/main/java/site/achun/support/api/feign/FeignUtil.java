package site.achun.support.api.feign;

import feign.Feign;

public class FeignUtil {

    private static String defaultUrl;

    public static void setDefaultUrl(String defaultUrl) {
        FeignUtil.defaultUrl = defaultUrl;
    }

    public static <T> T get(Class<T> clazz){
        return get(clazz,defaultUrl);
    }

    public static <T> T get(Class<T> clazz,String url){
        return Feign.builder()
                .encoder(new JsonEncoder())
                .decoder(new RspDecoder())
                .target(clazz, url);
    }


}
