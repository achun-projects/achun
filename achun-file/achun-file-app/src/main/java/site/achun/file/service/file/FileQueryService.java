package site.achun.file.service.file;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import site.achun.file.client.module.dir.request.ByDirCode;
import site.achun.file.client.module.file.request.*;
import site.achun.file.client.module.file.response.FileInfoResponse;
import site.achun.file.client.module.file.response.FileLocalInfoResponse;
import site.achun.file.generator.domain.FileDir;
import site.achun.file.generator.domain.FileInfo;
import site.achun.file.generator.service.FileDirService;
import site.achun.file.generator.service.FileInfoService;
import site.achun.file.service.dir.FileDirQueryService;
import site.achun.file.util.PageUtil;
import site.achun.support.api.enums.Deleted;
import site.achun.support.api.response.Rsp;
import site.achun.support.api.response.RspPage;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileQueryService {
    private final FileInfoService fileInfoService;
    private final FileConvert fileConvert;
    private final FileDirService fileDirService;

    public FileInfoResponse queryByCode(QueryByFileCode query){
        return fileConvert.toFileResponse(fileInfoService.getBy(query));
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

    public List<FileInfoResponse> queryFileList(QueryByMD5 query) {
        List<FileInfo> fileInfoList = fileInfoService.lambdaQuery()
                .eq(FileInfo::getMd5, query.getMd5())
                .eq(FileInfo::getDeleted, Deleted.NO.getStatus())
                .list();
        return fileConvert.toFileResponse(fileInfoList);
    }

    public RspPage<FileInfoResponse> queryFileList(QueryFilePageByDirCode query) {
        Page<FileInfo> pageResult = null;
        if(query.getOnlyThis()){
            pageResult = fileInfoService.lambdaQuery()
                    .eq(FileInfo::getDirCode,query.getDirCode())
                    .orderByDesc(FileInfo::getCtime)
                    .page(Page.of(query.getPage(), query.getSize()));
        }else{
            List<FileDir> dirList = fileDirService.queryDeepSub(query.getDirCode());
            List<String> dirCodes = dirList.stream().map(FileDir::getDirCode).collect(Collectors.toList());
            pageResult = fileInfoService.lambdaQuery()
                    .in(FileInfo::getDirCode,dirCodes)
                    .orderByDesc(FileInfo::getCtime)
                    .page(Page.of(query.getPage(), query.getSize()));
        }
        return PageUtil.batchParse(pageResult,query,fileConvert::toFileResponse);
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
        FileInfo fileInfo = fileInfoService.getBy(query);
        if(fileInfo==null){
            return null;
        }
        return fileConvert.toFileLocalInfoResponse(fileInfo);
    }

    public List<FileLocalInfoResponse> queryFileLocalInfoList(QueryByFileCodes query){
        return fileConvert.toFileLocalInfoResponse(fileInfoService.getByCodes(query.getFileCodes()));
    }
}
