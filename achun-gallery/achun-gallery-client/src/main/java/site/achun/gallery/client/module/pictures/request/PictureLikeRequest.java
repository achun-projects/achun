package site.achun.gallery.client.module.pictures.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Collection;

/**
 * Description
 *
 * @Author: Heiffeng
 * @Date: 2022/9/8 16:14
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PictureLikeRequest implements Serializable {
    private static final long serialVersionUID = 8544710008935427341L;

    private Collection<String> fileCodes;

    private String listCode;

    private String userCode;
}
