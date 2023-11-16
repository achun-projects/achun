package site.achun.video.app.service.business.video;

import cn.hutool.core.collection.CollUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import site.achun.file.client.module.dir.FileDirQueryClient;
import site.achun.file.client.module.dir.request.ByDirCodes;
import site.achun.file.client.module.dir.response.DirResponse;
import site.achun.file.client.module.file.FileQueryClient;
import site.achun.file.client.module.file.request.QueryByDirCode;
import site.achun.file.client.module.file.request.QueryByFileCode;
import site.achun.file.client.module.file.request.QueryFilePageByDirCode;
import site.achun.file.client.module.file.response.FileInfoResponse;
import site.achun.support.api.response.Rsp;
import site.achun.support.api.response.RspPage;
import site.achun.video.client.constant.ViewLevelEnum;
import site.achun.video.client.module.video.request.CreateOrUpdateVideoRequest;
import site.achun.video.client.module.video.request.CreateVideoFromCode;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class VideoCreateFromCodeService {

    private final FileQueryClient fileQueryClient;
    private final FileDirQueryClient fileDirQueryClient;
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
    public void createFromParentDirCodes(CreateVideoFromCode req){
        for (String code : req.getCodes()) {
            int page = 1,size = 100;
            List<FileInfoResponse> list = null;
            while((list = get(code,page++,size))!=null){
                for (FileInfoResponse file : list) {
                    CreateOrUpdateVideoRequest request = new CreateOrUpdateVideoRequest();
                    request.setChannelCode(req.getChannelCode());
                    request.setVideoCode(file.getFileCode());
                    request.setVideoFiles(Arrays.asList(file));
                    request.setUserCode(req.getUserCode());
                    request.setTitle(file.getFileName());
                    request.setViewLevel(req.getViewLevel());
                    videoUpdateService.createOrUpdateVideo(request);
                }
            }
        }
    }
    private List<FileInfoResponse> get(String dirCode, int page, int size){
        QueryFilePageByDirCode req = new QueryFilePageByDirCode();
        req.setDirCode(dirCode);
        req.setOnlyThis(false);
        req.setPage(page);
        req.setSize(size);
        Rsp<RspPage<FileInfoResponse>> response = fileQueryClient.queryFilePage(req);
        if(response.hasData() && CollUtil.isNotEmpty(response.getData().getRows())){
            return response.getData().getRows();
        }else{
            return null;
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
