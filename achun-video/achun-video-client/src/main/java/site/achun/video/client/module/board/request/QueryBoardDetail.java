package site.achun.video.client.module.board.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class QueryBoardDetail implements Serializable {
    private String boardCode;
}
