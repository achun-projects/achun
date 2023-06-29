package site.achun.video.client.module.list.request;

import lombok.Data;

import java.io.Serializable;

/**
 * Description
 *
 * @Author: Heiffeng
 * @Date: 2022/4/25 18:36
 */
@Data
public class UpdateListBaseInfo implements Serializable {

    private String listType;
    private String code;
    private String name;
    private String description;

}
