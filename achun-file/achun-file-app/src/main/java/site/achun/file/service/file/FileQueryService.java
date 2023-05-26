package site.achun.file.service.file;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import site.achun.file.client.module.file.request.QueryByFileCode;
import site.achun.file.client.module.file.request.QueryByFileCodes;
import site.achun.file.client.module.file.request.QueryFilePage;
import site.achun.file.client.module.file.response.FileInfoResponse;
import site.achun.file.client.module.file.response.FileLocalInfoResponse;
import site.achun.file.generator.domain.FileInfo;
import site.achun.file.generator.service.FileInfoService;
import site.achun.file.util.PageUtil;
import site.achun.support.api.enums.Deleted;
import site.achun.support.api.response.RspPage;

import java.util.Collection;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileQueryService {
    private final FileInfoService fileInfoService;
    private final FileConvert fileConvert;

    public FileInfoResponse queryByCode(String fileCode){
        return fileConvert.toFileResponse(fileInfoService.getByCode(fileCode));
    }

    public List<FileInfoResponse> queryByCodes(Collection<String> fileCodes){
        return fileConvert.toFileResponse(fileInfoService.getByCodes(fileCodes));
    }

    public List<FileInfoResponse> queryByUnitCode(String unitCode){
        List<FileInfo> fileInfoList = fileInfoService.lambdaQuery()
                .eq(FileInfo::getUnitCode, unitCode)
                .eq(FileInfo::getDeleted, Deleted.NO.getStatus())
                .list();
        return fileConvert.toFileResponse(fileInfoList);
    }

    public List<FileInfoResponse> queryByUnitCodes(Collection<String> unitCodes){
        List<FileInfo> fileInfoList = fileInfoService.lambdaQuery()
                .in(FileInfo::getUnitCode, unitCodes)
                .eq(FileInfo::getDeleted, Deleted.NO.getStatus())
                .list();
        return fileConvert.toFileResponse(fileInfoList);
    }

    public RspPage<FileLocalInfoResponse> queryFileLocalInfoPage(QueryFilePage query){
        Page<FileInfo> pageResult = fileInfoService.lambdaQuery()
                .eq(StrUtil.isNotBlank(query.getStorageCode()), FileInfo::getStorageCode, query.getStorageCode())
                .orderByDesc(FileInfo::getCtime)
                .page(Page.of(query.getPage(), query.getSize()));
        return PageUtil.batchParse(pageResult,query,fileConvert::toFileLocalInfoResponse);
    }

    public FileLocalInfoResponse queryFileLocalInfo(QueryByFileCode query){
        FileInfo fileInfo = fileInfoService.getByCode(query.getFileCode());
        return fileConvert.toFileLocalInfoResponse(fileInfo);
    }

    public List<FileLocalInfoResponse> queryFileLocalInfoList(QueryByFileCodes query){
        return fileConvert.toFileLocalInfoResponse(fileInfoService.getByCodes(query.getFileCodes()));
    }
}
