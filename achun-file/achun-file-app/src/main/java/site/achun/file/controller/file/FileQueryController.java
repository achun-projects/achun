package site.achun.file.controller.file;

import cn.hutool.core.collection.CollUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import site.achun.file.client.module.file.FileQueryClient;
import site.achun.file.client.module.file.request.*;
import site.achun.file.client.module.file.response.FileInfoResponse;
import site.achun.file.client.module.file.response.FileLocalInfoResponse;
import site.achun.file.service.file.FileQueryService;
import site.achun.support.api.response.Rsp;
import site.achun.support.api.response.RspPage;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Tag(name = "文件查询")
@RequiredArgsConstructor
@RestController
public class FileQueryController implements FileQueryClient {

    private final FileQueryService fileQueryService;
    @Override
    public Rsp<FileInfoResponse> queryFile(QueryByFileCode queryByFileCode) {
        return Rsp.success(fileQueryService.queryByCode(queryByFileCode.getFileCode()));
    }

    @Override
    public Rsp<Map<String, FileInfoResponse>> queryFileMap(QueryByFileCodes queryByFileCodes) {
        List<FileInfoResponse> list = fileQueryService.queryByCodes(queryByFileCodes.getFileCodes());
        if(CollUtil.isEmpty(list)){
            return Rsp.success(null);
        }
        Map<String, FileInfoResponse> map = list.stream()
                .collect(Collectors.toMap(FileInfoResponse::getFileCode, Function.identity(), (k1, k2) -> k1));
        return Rsp.success(map);
    }

    @Override
    public Rsp<Map<String, FileInfoResponse>> queryFileMap(QueryByUnitCodes queryByUnitCodes) {
        List<FileInfoResponse> list = fileQueryService.queryByUnitCodes(queryByUnitCodes.getUnitCodes());
        if(CollUtil.isEmpty(list)){
            return Rsp.success(null);
        }
        Map<String, FileInfoResponse> map = list.stream()
                .collect(Collectors.toMap(FileInfoResponse::getFileCode, Function.identity(), (k1, k2) -> k1));
        return Rsp.success(map);
    }

    @Override
    public Rsp<Map<String, FileInfoResponse>> queryFileMap(QueryByUnitCode queryByUnitCode) {
        List<FileInfoResponse> list = fileQueryService.queryByUnitCode(queryByUnitCode.getUnitCode());
        if(CollUtil.isEmpty(list)){
            return Rsp.success(null);
        }
        Map<String, FileInfoResponse> map = list.stream()
                .collect(Collectors.toMap(FileInfoResponse::getFileCode, Function.identity(), (k1, k2) -> k1));
        return Rsp.success(map);
    }

    @Override
    public Rsp<List<FileInfoResponse>> queryFileList(QueryByFileCodes queryByFileCodes) {
        return Rsp.success(fileQueryService.queryByCodes(queryByFileCodes.getFileCodes()));
    }

    @Override
    public Rsp<List<FileInfoResponse>> queryFileList(QueryByUnitCode queryByUnitCode) {
        return Rsp.success(fileQueryService.queryByUnitCode(queryByUnitCode.getUnitCode()));
    }

    @Override
    public Rsp<List<FileInfoResponse>> queryFileList(QueryByUnitCodes queryByUnitCodes) {
        return Rsp.success(fileQueryService.queryByUnitCodes(queryByUnitCodes.getUnitCodes()));
    }

    @Override
    public Rsp<RspPage<FileLocalInfoResponse>> queryFileLocalInfoPage(QueryFilePage query) {
        return Rsp.success(fileQueryService.queryFileLocalInfoPage(query));
    }

    @Override
    public Rsp<FileLocalInfoResponse> queryFileLocalInfo(QueryByFileCode query) {
        return Rsp.success(fileQueryService.queryFileLocalInfo(query));
    }
}
