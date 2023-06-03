package site.achun.file.controller.file;

import cn.hutool.core.collection.CollUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import site.achun.file.client.module.file.MediaFileQueryClient;
import site.achun.file.client.module.file.request.QueryByFileCode;
import site.achun.file.client.module.file.request.QueryByFileCodes;
import site.achun.file.client.module.file.request.QueryByUnitCode;
import site.achun.file.client.module.file.request.QueryByUnitCodes;
import site.achun.file.client.module.file.response.MediaFileResponse;
import site.achun.file.service.file.MediaFileQueryService;
import site.achun.support.api.response.Rsp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Tag(name = "多媒体文件查询")
@RequiredArgsConstructor
@RestController
public class MediaFileQueryController implements MediaFileQueryClient {

    private final MediaFileQueryService fileQueryService;
    @Override
    public Rsp<MediaFileResponse> queryFile(QueryByFileCode queryByFileCode) {
        return Rsp.success(fileQueryService.queryByCode(queryByFileCode.getFileCode()));
    }

    @Override
    public Rsp<Map<String, MediaFileResponse>> queryFileMap(QueryByFileCodes queryByFileCodes) {
        if(CollUtil.isEmpty(queryByFileCodes.getFileCodes())){
            return Rsp.success(new HashMap<>());
        }
        List<MediaFileResponse> list = fileQueryService.queryByCodes(queryByFileCodes.getFileCodes());
        if(CollUtil.isEmpty(list)){
            return Rsp.success(new HashMap<>());
        }
        Map<String, MediaFileResponse> map = list.stream()
                .collect(Collectors.toMap(MediaFileResponse::getFileCode, Function.identity(), (k1, k2) -> k1));
        return Rsp.success(map);
    }

    @Override
    public Rsp<Map<String, MediaFileResponse>> queryFileMap(QueryByUnitCodes queryByUnitCodes) {
        if(CollUtil.isEmpty(queryByUnitCodes.getUnitCodes())){
            return Rsp.success(new HashMap<>());
        }
        List<MediaFileResponse> list = fileQueryService.queryByUnitCodes(queryByUnitCodes.getUnitCodes());
        if(CollUtil.isEmpty(list)){
            return Rsp.success(new HashMap<>());
        }
        Map<String, MediaFileResponse> map = list.stream()
                .collect(Collectors.toMap(MediaFileResponse::getFileCode, Function.identity(), (k1, k2) -> k1));
        return Rsp.success(map);
    }

    @Override
    public Rsp<Map<String, MediaFileResponse>> queryFileMap(QueryByUnitCode queryByUnitCode) {
        List<MediaFileResponse> list = fileQueryService.queryByUnitCode(queryByUnitCode.getUnitCode());
        if(CollUtil.isEmpty(list)){
            return Rsp.success(null);
        }
        Map<String, MediaFileResponse> map = list.stream()
                .collect(Collectors.toMap(MediaFileResponse::getFileCode, Function.identity(), (k1, k2) -> k1));
        return Rsp.success(map);
    }

    @Override
    public Rsp<List<MediaFileResponse>> queryFileList(QueryByFileCodes queryByFileCodes) {
        return Rsp.success(fileQueryService.queryByCodes(queryByFileCodes.getFileCodes()));
    }

    @Override
    public Rsp<List<MediaFileResponse>> queryFileList(QueryByUnitCode queryByUnitCode) {
        return Rsp.success(fileQueryService.queryByUnitCode(queryByUnitCode.getUnitCode()));
    }

    @Override
    public Rsp<List<MediaFileResponse>> queryFileList(QueryByUnitCodes queryByUnitCodes) {
        return Rsp.success(fileQueryService.queryByUnitCodes(queryByUnitCodes.getUnitCodes()));
    }

}
