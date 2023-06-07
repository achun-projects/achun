package site.achun.gallery.client.module.board.request;

import lombok.Data;

import java.io.Serializable;

/**
 * Description
 *
 * @Author: Heiffeng
 * @Date: 2022/1/12 16:39
 */
@Data
public class CopyBoardRecordRequest extends MoveBoardRecordRequest implements Serializable {
    private static final long serialVersionUID = 8464986982718401658L;
}
