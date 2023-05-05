package site.achun.support.api.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.net.InetSocketAddress;
import java.net.Proxy;

/**
 * Description
 *
 * @Author: SunAo
 * @Date: 2021/7/26 15:31
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProxyInfo implements Serializable {

    private static final long serialVersionUID = 7333941947298827072L;

    private String host;
    private Integer port;

    public Proxy buildProxy(){
        if(host==null||port==null) return null;
        return new Proxy(Proxy.Type.HTTP, InetSocketAddress.createUnresolved(host,port));
    }

}
