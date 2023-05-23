package site.achun.updown.app.service.module.transfer;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Description
 *
 * @Author: Heiffeng
 * @Date: 2022/8/5 17:25
 */
@Getter
@AllArgsConstructor
public enum TransferType {

    GIF_PARAMS(1,"GIF文件参数"),
    JPG_PARAMS_AND_RESIZE(2,"JPG参数和缩略图"),
    MP4_PARAMS(3,"MP4参数"),
    PHASH_CAL(4,"图片PHASH计算"),

    VIDEO_TRANSCODE(5,"视频转码");

    public final static Collection<TransferType> ALL = Arrays.stream(TransferType.values()).collect(Collectors.toList());

    private Integer code;
    private String name;
}
