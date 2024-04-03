package site.achun.file.client.alist.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import site.achun.file.client.alist.response.Resp;

@Data
@AllArgsConstructor
public class AListException extends RuntimeException{

    private Resp resp;

}
