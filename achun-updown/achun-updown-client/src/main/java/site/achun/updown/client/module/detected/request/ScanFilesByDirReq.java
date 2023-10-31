package site.achun.updown.client.module.detected.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class ScanFilesByDirReq implements Serializable {
    private String dirCode;
}
