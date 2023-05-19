package site.achun.updown.app.service.module.detected;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import site.achun.file.client.module.file.FileQueryClient;
import site.achun.file.client.module.file.request.QueryFilePage;
import site.achun.file.client.module.file.response.FileLocalInfoResponse;
import site.achun.support.api.response.RspPage;

@Slf4j
@Service
@RequiredArgsConstructor
public class LocalFileDetectedByDatabaseService {


    private final FileQueryClient fileQueryClient;

    public void detectedByDatabase(){
        QueryFilePage query = new QueryFilePage();
        query.setPage(1);
        query.setSize(100);
        query.setStorageCode("");

        RspPage<FileLocalInfoResponse> rsp = fileQueryClient.queryFileLocalInfoPage(query).getData();
        for (FileLocalInfoResponse row : rsp.getRows()) {
            System.out.println(row.getFileName());
        }
    }

}
