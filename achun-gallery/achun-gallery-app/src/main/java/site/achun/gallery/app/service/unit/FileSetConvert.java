package site.achun.gallery.app.service.unit;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import site.achun.gallery.app.generator.domain.FileSet;
import site.achun.gallery.client.module.pic_unit.response.PicUnitResponse;

/**
 * Author: Heiffeng
 * Date: 2023/3/27
 */
@Slf4j
@Service
@AllArgsConstructor
public class FileSetConvert {

    public PicUnitResponse toResponse(FileSet fileSet){
        return PicUnitResponse.builder()
                .name(fileSet.getName())
                .code(fileSet.getCode())
                .userCode(fileSet.getUserCode())
                .build();
    }
}
