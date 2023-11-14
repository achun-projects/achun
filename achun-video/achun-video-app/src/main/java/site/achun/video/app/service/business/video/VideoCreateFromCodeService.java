package site.achun.video.app.service.business.video;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import site.achun.file.client.module.file.FileQueryClient;
import site.achun.file.client.module.file.request.QueryByDirCode;
import site.achun.file.client.module.file.request.QueryByFileCode;
import site.achun.file.client.module.file.response.FileInfoResponse;
import site.achun.video.client.constant.ViewLevelEnum;
import site.achun.video.client.module.video.request.CreateOrUpdateVideoRequest;
import site.achun.video.client.module.video.request.CreateVideoFromCode;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class VideoCreateFromCodeService {

    private final FileQueryClient fileQueryClient;
    private final VideoUpdateService videoUpdateService;
    public void createFromDirCodes(CreateVideoFromCode req){
        for (String dirCode : req.getCodes()) {
            createVideoFromDirCode(dirCode,req.getChannelCode(),req.getViewLevel(),req.getUserCode());
        }
    }

    private void createVideoFromDirCode(String dirCode, String channelCode, ViewLevelEnum viewLevel,String userCode){
        QueryByDirCode query = QueryByDirCode.builder()
                .dirCode(dirCode)
                .build();
        List<FileInfoResponse> list = fileQueryClient.queryFileList(query).getData();

        CreateOrUpdateVideoRequest request = new CreateOrUpdateVideoRequest();
        request.setChannelCode(channelCode);
        request.setVideoCode(dirCode);
        request.setVideoFiles(list);
        request.setUserCode(userCode);
        request.setTitle(dirCode);
        request.setViewLevel(viewLevel);
        videoUpdateService.createOrUpdateVideo(request);
    }

    public void createFromFileCodes(CreateVideoFromCode req){
        for (String fileCode : req.getCodes()) {
            createVideoFromFileCode(fileCode,req.getChannelCode(),req.getViewLevel(),req.getUserCode());
        }
    }

    private void createVideoFromFileCode(String fileCode,String channelCode, ViewLevelEnum viewLevel,String userCode){
        QueryByFileCode query = QueryByFileCode.builder()
                .fileCode(fileCode)
                .build();
        FileInfoResponse file = fileQueryClient.queryFile(query).getData();

        CreateOrUpdateVideoRequest request = new CreateOrUpdateVideoRequest();
        request.setChannelCode(channelCode);
        request.setVideoCode(file.getFileCode());
        request.setVideoFiles(Arrays.asList(file));
        request.setUserCode(userCode);
        request.setTitle(file.getFileName());
        request.setViewLevel(viewLevel);
        videoUpdateService.createOrUpdateVideo(request);
    }
}
