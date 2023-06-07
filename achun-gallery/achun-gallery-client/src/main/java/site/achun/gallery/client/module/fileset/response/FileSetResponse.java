package site.achun.gallery.client.module.fileset.response;

import lombok.*;

import java.io.Serializable;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileSetResponse implements Serializable {

    /**
     * 分组唯一标识
     */
    private String code;

    /**
     * 用户code
     */
    private String userCode;

    /**
     * 分组名称
     */
    private String name;
}
