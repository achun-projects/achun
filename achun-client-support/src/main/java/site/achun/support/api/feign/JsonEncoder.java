package site.achun.support.api.feign;

import com.alibaba.fastjson2.JSON;
import feign.Request;
import feign.RequestTemplate;
import feign.codec.EncodeException;
import feign.codec.Encoder;

import java.lang.reflect.Type;

public class JsonEncoder implements Encoder {
    @Override
    public void encode(Object object, Type type, RequestTemplate template) throws EncodeException {
        if (object != null) {
            template.body(Request.Body.create(JSON.toJSONString(object)));
        }
    }
}