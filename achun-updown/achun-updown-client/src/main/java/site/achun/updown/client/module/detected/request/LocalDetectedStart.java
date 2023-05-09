package site.achun.updown.client.module.detected.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class LocalDetectedStart implements Serializable {

    // 本地绝对路径
    private String localPath;


}
