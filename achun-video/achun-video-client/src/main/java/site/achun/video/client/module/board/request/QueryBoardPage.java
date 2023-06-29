package site.achun.video.client.module.board.request;

import lombok.Data;
import site.achun.support.api.request.ReqPage;

import java.io.Serializable;

@Data
public class QueryBoardPage extends ReqPage implements Serializable {
    private String group;
    private String userCode;
}
