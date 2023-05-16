package site.achun.file.controller.file;

import cn.hutool.core.bean.BeanUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import site.achun.file.client.module.file.FileQueryClient;
import site.achun.file.client.module.file.request.QueryByFileCode;
import site.achun.file.client.module.file.request.QueryByFileCodes;
import site.achun.file.client.module.file.request.QueryByUnitCode;
import site.achun.file.client.module.file.request.QueryByUnitCodes;
import site.achun.file.client.module.file.response.FileInfoResponse;
import site.achun.file.generator.domain.FileInfo;
import site.achun.file.generator.service.FileInfoService;
import site.achun.support.api.response.Rsp;

import java.util.List;
import java.util.Map;

@Slf4j
@Tag(name = "文件查询")
@RequiredArgsConstructor
@RestController
public class FileQueryController implements FileQueryClient {

    private final FileInfoService fileInfoService;
    @Override
    public Rsp<FileInfoResponse> queryFile(QueryByFileCode queryByFileCode) {
        FileInfo fileInfo = fileInfoService.lambdaQuery()
                .eq(FileInfo::getFileCode, queryByFileCode.getFileCode())
                .one();
        FileInfoResponse resp = BeanUtil.toBean(fileInfo, FileInfoResponse.class);
        return Rsp.success(resp);
    }

    @Override
    public Rsp<Map<String, FileInfoResponse>> queryFileMap(QueryByFileCodes queryByFileCodes) {
        return null;
    }

    @Override
    public Rsp<Map<String, FileInfoResponse>> queryFileMap(QueryByUnitCodes queryByUnitCodes) {
        return null;
    }

    @Override
    public Rsp<Map<String, FileInfoResponse>> queryFileMap(QueryByUnitCode queryByUnitCode) {
        return null;
    }

    @Override
    public Rsp<List<FileInfoResponse>> queryFileList(QueryByFileCodes queryByFileCodes) {
        return null;
    }

    @Override
    public Rsp<List<FileInfoResponse>> queryFileList(QueryByUnitCode queryByUnitCode) {
        return null;
    }

    @Override
    public Rsp<List<FileInfoResponse>> queryFileList(QueryByUnitCodes queryByUnitCodes) {
        return null;
    }
}
