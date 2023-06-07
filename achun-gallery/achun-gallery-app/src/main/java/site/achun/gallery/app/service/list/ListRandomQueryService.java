package site.achun.gallery.app.service.list;

import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import site.achun.gallery.app.generator.domain.Pictures;
import site.achun.gallery.app.generator.mapper.PicturesMapper;
import site.achun.gallery.client.module.pictures.request.QueryFilesByListCodes;

import java.util.Arrays;
import java.util.Collection;

@Slf4j
@Service
@RequiredArgsConstructor
public class ListRandomQueryService {

    private final ListFilesQueryExecute listFilesQueryExecute;
    public String randomQuery(String listCode){
        return randomQuery(Arrays.asList(listCode));
    }

    public String randomQuery(Collection<String> listCodes){
        // 通过相册和画板查询到文件总数。使用总数获取随机值。
        IPage<Pictures> pageResult = listFilesQueryExecute.queryPages(listCodes,1,1);
        if(pageResult.getTotal() == 0){
            return null;
        }
        long randomNum = RandomUtil.randomLong(pageResult.getTotal());
        pageResult = listFilesQueryExecute.queryPages(listCodes,randomNum,1);
        return pageResult.getRecords().get(0).getFileCode();
    }
}
