package site.achun.video.app.service.unit;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import site.achun.video.app.generator.domain.FileSet;
import site.achun.video.client.module.fileset.response.FileSetResponse;

/**
 * Author: Heiffeng
 * Date: 2023/3/27
 */
@Slf4j
@Service
@AllArgsConstructor
public class FileSetConvert {

    public FileSetResponse toResponse(FileSet fileSet){
        return FileSetResponse.builder()
                .name(fileSet.getName())
                .code(fileSet.getCode())
                .userCode(fileSet.getUserCode())
                .build();
    }
}
