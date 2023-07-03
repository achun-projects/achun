package site.achun.gallery.client.module.board_record.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class RandomQueryOneBoardRecord implements Serializable {

    private String boardCode;
}
