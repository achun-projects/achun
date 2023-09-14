package site.achun.gallery.app.service.list;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import site.achun.file.client.module.file.MediaFileQueryClient;
import site.achun.file.client.module.file.request.QueryByFileCode;
import site.achun.file.client.module.file.response.MediaFileResponse;
import site.achun.gallery.app.generator.domain.Pictures;
import site.achun.gallery.app.service.pictures.PictureConvertService;
import site.achun.gallery.client.module.pictures.response.Photo;
import site.achun.support.api.response.Rsp;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ListRandomQueryService {

    private final ListFilesQueryExecute listFilesQueryExecute;
    private final MediaFileQueryClient mediaFileQueryClient;
    private final PictureConvertService pictureConvertService;
    public String randomQuery(String listCode){
        return randomQuery(Arrays.asList(listCode));
    }

    public String randomQuery(Collection<String> listCodes){
        List<Pictures> list = randomQuery(listCodes, 1);
        if(CollUtil.isEmpty(list)){
            return null;
        }
        return list.get(0).getFileCode();
    }

    public List<Pictures> randomQuery(Collection<String> listCodes, long size){
        // 通过相册和画板查询到文件总数。使用总数获取随机值。
        IPage<Pictures> pageResult = listFilesQueryExecute.queryPages(listCodes,1,1);
        if(pageResult.getTotal() == 0){
            return null;
        }
        long randomNum = RandomUtil.randomLong(pageResult.getTotal());
        pageResult = listFilesQueryExecute.queryPages(listCodes,randomNum,size);
        return pageResult.getRecords();
    }

    public Photo randomQueryOnePhoto(String listCode){
        String fileCode = randomQuery(listCode);
        if(fileCode == null){
            return null;
        }
        Rsp<MediaFileResponse> fileResponse = mediaFileQueryClient.queryFile(QueryByFileCode.builder().fileCode(fileCode).build());
        if(fileResponse.hasData()){
            return pictureConvertService.toPhoto(fileResponse.getData());
        }
        return null;
    }
}
