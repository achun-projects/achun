package site.achun.gallery.app.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import site.achun.file.client.module.unit.request.UpdateUnit;
import site.achun.gallery.app.generator.domain.FileSet;
import site.achun.gallery.app.generator.service.FileSetService;
import site.achun.gallery.client.module.unit.request.UpdateFileUnit;
import site.achun.support.api.enums.Deleted;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileUnitUpdateService {

    private final FileSetService fileSetService;

    public void updateUnit(UpdateUnit update){
        FileSet fileUnit = fileSetService.lambdaQuery()
                .eq(FileSet::getCode, update.getUnitCode())
                .last("limit 1")
                .one();
        if(fileUnit == null){
            fileUnit = new FileSet();
            fileUnit.setCode(update.getUnitCode());
            fileUnit.setName(update.getUnitName());
            fileUnit.setDeleted(Deleted.NO.getStatus());
            fileUnit.setCtime(LocalDateTime.now());
            fileUnit.setUserCode("1");
            fileUnit.setUtime(LocalDateTime.now());
            fileSetService.save(fileUnit);
        }else{
            fileUnit.setName(update.getUnitName());
            fileUnit.setUtime(LocalDateTime.now());
            fileSetService.updateById(fileUnit);
        }
    }
}
