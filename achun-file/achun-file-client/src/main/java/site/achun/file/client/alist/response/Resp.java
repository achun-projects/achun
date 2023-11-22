package site.achun.file.client.alist.response;

import lombok.Data;

@Data
public class Resp<T> {
    private Integer code;
    private String message;
    private T data;
}
