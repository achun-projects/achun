package site.achun.file.client.alist.response;

import lombok.Data;

@Data
public class FSResponse {
    private String name; // 文件名
    private Integer size; // 大小
    private Boolean is_dir; // 是否是文件夹
    private String modified; // 修改时间
    private String sign; // 签名
    private String thumb; // 缩略图
    private Integer type; // 类型
    private String raw_url; // 原始url
    private String readme; // 说明
    private String provider;
    private Object related;
}
