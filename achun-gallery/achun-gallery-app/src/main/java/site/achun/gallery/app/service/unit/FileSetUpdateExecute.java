package site.achun.gallery.app.service.unit;

import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import site.achun.gallery.app.generator.domain.FileSet;
import site.achun.gallery.app.generator.service.FileSetService;
import site.achun.gallery.client.module.fileset.request.CreateFileSet;
import site.achun.support.api.enums.Deleted;
import site.achun.support.api.utils.CodeGenUtil;

import java.time.LocalDateTime;

@Slf4j
@Service
@AllArgsConstructor
public class FileSetUpdateExecute {

    private final FileSetService fileSetService;

    public String createOrUpdate(CreateFileSet create){
        FileSet existFileSet = null;
        if(StrUtil.isNotEmpty(create.getSetCode()) && (existFileSet = fileSetService.getByCode(create.getSetCode())) != null){
            if(StrUtil.isNotEmpty(create.getName())) existFileSet.setName(create.getName());
            if(StrUtil.isNotEmpty(create.getUserCode())) existFileSet.setUserCode(create.getUserCode());
            existFileSet.setUtime(LocalDateTime.now());
            fileSetService.updateById(existFileSet);
            return create.getSetCode();
        }else{
            FileSet fileSet = FileSet.builder()
                    .code(StrUtil.isNotEmpty(create.getSetCode()) ? create.getSetCode() : CodeGenUtil.uuid())
                    .name(create.getName())
                    .userCode(create.getUserCode())
                    .deleted(Deleted.NO.getStatus())
                    .utime(LocalDateTime.now())
                    .ctime(LocalDateTime.now())
                    .build();
            fileSetService.save(fileSet);
            return fileSet.getCode();
        }
    }
}
