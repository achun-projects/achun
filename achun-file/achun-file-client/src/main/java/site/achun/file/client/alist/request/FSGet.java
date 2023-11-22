package site.achun.file.client.alist.request;

import lombok.Data;

@Data
public class FSGet {
    private String path;
    private String password;
    private Integer page;
    private Integer perPage;
    private Boolean refresh;
}
