package site.achun.updown.app.controller.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Description
 *
 * @Author: Heiffeng
 * @Date: 2022/9/26 16:11
 */
@Getter
@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum UploadStatus {

    NEVER_UPLOAD(0,"未上传"),
    CONTINUE_UPLOAD(1,"需要断点续传"),
    EXIST(2,"已存在，不需要重复上传");

    private Integer type;
    private String desc;
}
