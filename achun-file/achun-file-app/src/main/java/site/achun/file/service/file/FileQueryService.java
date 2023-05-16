package site.achun.file.service.file;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import site.achun.file.client.module.file.response.FileInfoResponse;
import site.achun.file.generator.service.FileInfoService;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileQueryService {
    private final FileInfoService fileInfoService;
    private final FileConvert fileConvert;

    public FileInfoResponse queryByCode(String fileCode){
        return fileConvert.toFileResponse(fileInfoService.getByCode(fileCode));
    }
}
