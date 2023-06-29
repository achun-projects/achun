package site.achun.video.client.module.video.response;

import lombok.*;

import java.io.Serializable;

/**
 * Description
 *
 * @Author: Heiffeng
 * @Date: 2022/3/17 13:46
 */
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Video implements Serializable {

    private static final long serialVersionUID = 5579870622782937697L;

    private String fileCode;
    private String fileName;
    private String videoCode;
    private String videoName;
    private Integer type;
    private String url;
    private String cover;

    private VideoInfoResponse videoResponse;
}
