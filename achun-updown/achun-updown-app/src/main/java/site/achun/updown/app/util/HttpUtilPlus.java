package site.achun.updown.app.util;

import cn.hutool.core.io.StreamProgress;
import cn.hutool.core.lang.Assert;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;

import java.io.File;
import java.net.Proxy;

/**
 * @Author: SunAo
 * @Date: 2020/12/2 16:26
 * @Description:
 */
public class HttpUtilPlus extends HttpUtil {
    /**
     * 下载远程文件
     *
     * @param url            请求的url
     * @param destFile       目标文件或目录，当为目录时，取URL中的文件名，取不到使用编码后的URL做为文件名
     * @param timeout        超时，单位毫秒，-1表示默认超时
     * @param streamProgress 进度条
     * @return 文件大小
     * @since 4.0.4
     */
    public static HttpResponse downloadFile(String url, File destFile, int timeout, StreamProgress streamProgress, Proxy proxy) {
        HttpResponse response = requestDownloadFile(url, destFile, timeout, proxy);
        response.writeBody(destFile, streamProgress);
        return response;
    }
    /**
     * 请求下载文件
     *
     * @param url      请求下载文件地址
     * @param destFile 目标目录或者目标文件
     * @param timeout  超时时间
     *
     * @return HttpResponse
     * @since 5.4.1
     */
    private static HttpResponse requestDownloadFile(String url, File destFile, int timeout, Proxy proxy) {
        Assert.notBlank(url, "[url] is blank !");
        Assert.notNull(url, "[destFile] is null !");

        HttpRequest http = null;
        if(proxy!=null){
            http = HttpRequest.get(url).setProxy(proxy).timeout(timeout);
        }else{
            http = HttpRequest.get(url).timeout(timeout);
        }
        if(url.contains("sinaimg")){
            http.header("referer","https://weibo.com");
        }
        final HttpResponse response = http.executeAsync();
        return response;
    }
}
