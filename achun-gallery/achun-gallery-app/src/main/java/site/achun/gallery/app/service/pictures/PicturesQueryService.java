package site.achun.gallery.app.service.pictures;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import site.achun.file.client.module.file.FileQueryClient;
import site.achun.file.client.module.file.request.QueryByFileCode;
import site.achun.file.client.module.file.response.FileInfoResponse;
import site.achun.gallery.client.module.pictures.response.PicturesBasicInfo;
import site.achun.support.api.response.Rsp;

@Slf4j
@Service
@RequiredArgsConstructor
public class PicturesQueryService {

    private final FileQueryClient fileQueryClient;

    public PicturesBasicInfo queryBasicInfo(String fileCode){
        Rsp<FileInfoResponse> rsp = fileQueryClient.queryFile(QueryByFileCode.builder().fileCode(fileCode).build());
        if(!rsp.hasData()){
            return null;
        }
        FileInfoResponse fileInfo = rsp.getData();
        PicturesBasicInfo pic = new PicturesBasicInfo();
        pic.setFileCode(fileCode);
        pic.setUrl(fileInfo.getUrl());
        pic.setCover(fileInfo.getCover());
        return pic;
    }
}
