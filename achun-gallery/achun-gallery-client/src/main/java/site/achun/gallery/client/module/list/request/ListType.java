package site.achun.gallery.client.module.list.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Description
 *
 * @Author: Heiffeng
 * @Date: 2022/4/25 13:52
 **/
@Getter
@AllArgsConstructor
public enum ListType {

    ALBUM("1","相册"),
    BOARD("2","画板");

    private String code;
    private String value;
}
