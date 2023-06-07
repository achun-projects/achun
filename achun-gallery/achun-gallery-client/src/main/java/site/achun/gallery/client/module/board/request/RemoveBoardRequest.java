package site.achun.gallery.client.module.board.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.io.Serializable;

/**
 * Description
 *
 * @Author: Heiffeng
 * @Date: 2022/4/25 11:39
 */
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RemoveBoardRequest implements Serializable {

    @NotEmpty
    private String boardCode;
}
