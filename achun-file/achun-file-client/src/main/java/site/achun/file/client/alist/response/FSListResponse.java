package site.achun.file.client.alist.response;

import lombok.Data;

import java.util.List;

@Data
public class FSListResponse {
    private List<FSResponse> content;
    private Integer total; // 总数
    private String readme; // 说明
    private Boolean write; // 是否可写入
    private String provider;

}
