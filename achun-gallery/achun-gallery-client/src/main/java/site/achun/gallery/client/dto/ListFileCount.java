package site.achun.gallery.client.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * Description
 *
 * @Author: Heiffeng
 * @Date: 2022/8/2 18:19
 */
@Data
public class ListFileCount implements Serializable {
    private static final long serialVersionUID = 9194656315180499688L;
    private String code;
    private Integer fileCount;
}
