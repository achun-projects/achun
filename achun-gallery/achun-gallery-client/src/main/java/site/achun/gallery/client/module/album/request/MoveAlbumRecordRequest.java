package site.achun.gallery.client.module.album.request;

import lombok.*;

import java.io.Serializable;
import java.util.Collection;

/**
 * Description
 *
 * @Author: Heiffeng
 * @Date: 2022/1/12 17:43
 */
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MoveAlbumRecordRequest implements Serializable {
    private static final long serialVersionUID = -2725776514533407963L;

    // 文件集合
    private Collection<String> setCodes;
    private Collection<String> fileCodes;
    // 到哪儿去
    private String albumCode;
}
