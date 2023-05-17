package site.achun.file.service.file;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import site.achun.file.client.module.file.response.FileInfoResponse;
import site.achun.file.generator.domain.FileInfo;
import site.achun.file.generator.service.FileInfoService;
import site.achun.support.api.enums.Deleted;

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
}
