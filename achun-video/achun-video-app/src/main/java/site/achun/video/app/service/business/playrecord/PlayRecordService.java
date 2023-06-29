package site.achun.video.app.service.business.playrecord;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import site.achun.file.client.module.file.MediaFileQueryClient;
import site.achun.file.client.module.file.request.QueryByFileCodes;
import site.achun.file.client.module.file.response.MediaFileResponse;
import site.achun.support.api.response.Rsp;
import site.achun.video.app.service.business.video.convert.VideoConvert;
import site.achun.video.app.service.execute.playrecord.PlayRecordAddExecute;
import site.achun.video.app.service.execute.playrecord.PlayRecordQueryExecute;
import site.achun.video.client.module.record.request.AddPlayRecord;
import site.achun.video.client.module.record.response.PlayRecordResponse;
import site.achun.video.client.module.video.response.Video;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Description
 *
 * @Author: Heiffeng
 * @Date: 2022/3/30 22:59
 */
@Slf4j
@Service
@AllArgsConstructor
public class PlayRecordService {

    private final PlayRecordAddExecute playRecordAddExecute;
    private final PlayRecordQueryExecute playRecordQueryExecute;
    private final MediaFileQueryClient fileQueryClient;

    public Rsp<List<Video>> queryRecent(Integer limit){
        List<PlayRecordResponse> records = playRecordQueryExecute.queryRecent(limit).tryGetData();
        List<String> fileCodes = records.stream()
                .map(PlayRecordResponse::getVideoFileCode)
                .collect(Collectors.toList());
        Map<String, MediaFileResponse> fileMap = fileQueryClient.queryFileMap(QueryByFileCodes.builder().fileCodes(fileCodes).build()).tryGetData();
        List<Video> result = records.stream()
                .map(record -> VideoConvert.toVideo(fileMap.get(record.getVideoFileCode())))
                .collect(Collectors.toList());
        return Rsp.success(result);
    }

    public Rsp<PlayRecordResponse> newRecord(AddPlayRecord addPlayRecord) {
        return playRecordAddExecute.newRecord(addPlayRecord);
    }

}
