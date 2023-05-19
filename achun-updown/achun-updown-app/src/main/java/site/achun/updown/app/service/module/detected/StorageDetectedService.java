package site.achun.updown.app.service.module.detected;

import cn.hutool.core.io.FileUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class StorageDetectedService {

    public Boolean existFile(String pathString){
        return FileUtil.exist(pathString);
    }
}
