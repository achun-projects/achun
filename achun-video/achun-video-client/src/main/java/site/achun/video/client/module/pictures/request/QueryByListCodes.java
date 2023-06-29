package site.achun.video.client.module.pictures.request;

import lombok.*;

import java.io.Serializable;
import java.util.Collection;

/**
 * Description
 *
 * @Author: Heiffeng
 * @Date: 2022/8/2 17:46
 */
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QueryByListCodes implements Serializable {
    private static final long serialVersionUID = 8925785448842071578L;

    private Collection<String> listCodes;

}
