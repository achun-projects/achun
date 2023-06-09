package site.achun.gallery.client.module.unit;


import io.swagger.v3.oas.annotations.Operation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import site.achun.gallery.client.module.fileset.request.QueryFileSet;
import site.achun.gallery.client.module.fileset.response.FileSetResponse;
import site.achun.support.api.response.Rsp;

@FeignClient(name = "achun-gallery-app", contextId = "PicUnitQueryClient")
public interface PicUnitQueryClient {

    @Operation(summary = "查询文件单元")
    @PostMapping("/gallery/fileset/query-fileset")
    Rsp<FileSetResponse> queryFileSet(@RequestBody QueryFileSet request);
}
