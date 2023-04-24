package site.achun.file.app.controller.storage;

import cn.virde.common.pojo.rsp.Rsp;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import site.achun.file.api.modules.storage.response.StorageResponse;
import site.achun.file.app.service.storage.StorageQueryService;

import java.util.List;

@Tag(name = "存储查询")
@Order(3)
@Slf4j
@RestController
@RequiredArgsConstructor
public class StorageQueryController {

    private final StorageQueryService storageQueryService;

    @Operation(summary = "查询Storages")
    @GetMapping("/file/bucket/query-storages")
    public Rsp<List<StorageResponse>> queryStorages(){
        return Rsp.success(storageQueryService.queryStorages());
    }

}
