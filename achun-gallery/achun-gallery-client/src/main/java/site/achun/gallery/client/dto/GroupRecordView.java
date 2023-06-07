package site.achun.gallery.client.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * Description
 *
 * @Author: Heiffeng
 * @Date: 2022/1/28 15:03
 */
@Data
public class GroupRecordView implements Serializable {

    private String groupCode;

    private String listCode;

    private String name;

}
