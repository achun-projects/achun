package site.achun.gallery.app.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import site.achun.file.client.module.unit.request.UpdateUnit;
import site.achun.gallery.app.generator.domain.FileSet;
import site.achun.gallery.app.generator.service.FileSetService;
import site.achun.gallery.client.module.unit.request.UpdateFileUnit;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileUnitUpdateService {

    private final FileSetService fileSetService;

    public void updateUnit(UpdateUnit update){
        fileSetService.lambdaUpdate()
                .set(FileSet::getName,update.getUnitName())
                .set(FileSet::getUtime, LocalDateTime.now())
                .eq(FileSet::getCode,update.getUnitCode())
                .update();
    }
}
