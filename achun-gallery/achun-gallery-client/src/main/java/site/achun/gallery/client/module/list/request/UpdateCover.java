package site.achun.gallery.client.module.list.request;

import lombok.Data;

import java.io.Serializable;

/**
 * Description
 *
 * @Author: Heiffeng
 * @Date: 2022/4/25 13:50
 */
@Data
public class UpdateCover implements Serializable {

    private String listType;
    private String code;
    private String coverFileCode;
}
