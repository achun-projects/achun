package site.achun.gallery.client.module.album_record;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import site.achun.support.api.response.Rsp;

import java.util.Collection;


@FeignClient(name = "virde-gallery-web", contextId = "AlbumRecordUpdateV4Client")
public interface AlbumRecordUpdateV4Client {

    @PostMapping("/gallery/album-record/batch-delete")
    Rsp<Boolean> deleteBatchRecords(@RequestBody Collection<String> fileCodes);
}
