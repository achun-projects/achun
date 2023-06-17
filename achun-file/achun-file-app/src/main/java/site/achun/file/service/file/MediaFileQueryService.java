package site.achun.file.service.file;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import site.achun.file.client.module.file.request.QueryByFileCode;
import site.achun.file.client.module.file.response.MediaFileResponse;
import site.achun.file.generator.domain.FileInfo;
import site.achun.file.generator.service.FileInfoService;
import site.achun.support.api.enums.Deleted;

import java.util.Collection;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MediaFileQueryService {


    private final FileInfoService fileInfoService;
    private final FileConvert fileConvert;

    public MediaFileResponse queryByCode(QueryByFileCode query){
        return fileConvert.toMediaFileResponse(fileInfoService.getBy(query));
    }

    public List<MediaFileResponse> queryByCodes(Collection<String> fileCodes){
        return fileConvert.toMediaFileResponse(fileInfoService.getByCodes(fileCodes));
    }

    public List<MediaFileResponse> queryByUnitCode(String unitCode){
        List<FileInfo> fileInfoList = fileInfoService.lambdaQuery()
                .eq(FileInfo::getUnitCode, unitCode)
                .eq(FileInfo::getDeleted, Deleted.NO.getStatus())
                .list();
        return fileConvert.toMediaFileResponse(fileInfoList);
    }

    public List<MediaFileResponse> queryByUnitCodes(Collection<String> unitCodes){
        List<FileInfo> fileInfoList = fileInfoService.lambdaQuery()
                .in(FileInfo::getUnitCode, unitCodes)
                .eq(FileInfo::getDeleted, Deleted.NO.getStatus())
                .list();
        return fileConvert.toMediaFileResponse(fileInfoList);
    }

}
