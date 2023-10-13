package site.achun.support.api.feign;

import com.alibaba.fastjson2.JSON;
import feign.Response;
import feign.codec.Decoder;

import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;

public class RspDecoder implements Decoder {
    @Override
    public Object decode(Response response, Type type){
        Response.Body body = response.body();
        if (body == null) {
            return null; // No response body
        }
        try (Reader reader = body.asReader(StandardCharsets.UTF_8)) {
            return JSON.parseObject(reader, type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
