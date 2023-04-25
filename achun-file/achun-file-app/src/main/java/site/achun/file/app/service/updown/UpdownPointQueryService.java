package site.achun.file.app.service.updown;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import site.achun.file.api.modules.storage.enums.StorageStatus;
import site.achun.file.api.modules.updown.response.UpdownPointInfo;
import site.achun.file.app.generator.domain.Storage;
import site.achun.file.app.generator.service.StorageService;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class UpdownPointQueryService {

    private final StorageService storageService;
    public final static Map<String,String> POINT_MAP = Map.of(
            "updown1","http://localhost:8081",
            "updown2","http://localhost:8082",
            "updown3","http://localhost:8083"
    );

    public String queryOneAvailablePoint(String bucketCode){
        return POINT_MAP.get(bucketCode);
    }
}
