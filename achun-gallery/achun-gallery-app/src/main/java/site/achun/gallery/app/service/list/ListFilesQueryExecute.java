package site.achun.gallery.app.service.list;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import site.achun.gallery.app.generator.domain.Pictures;
import site.achun.gallery.app.generator.mapper.PicturesMapper;
import site.achun.gallery.client.module.pictures.request.QueryFilesByListCodes;
import site.achun.support.api.response.RspPage;

import java.util.Arrays;
import java.util.Collection;

@Slf4j
@Service
@RequiredArgsConstructor
public class ListFilesQueryExecute {

    private final PicturesMapper picturesMapper;

    public IPage<Pictures> queryPages(String listCode,long page,long size){
        QueryFilesByListCodes query = new QueryFilesByListCodes();
        query.setListCodes(Arrays.asList(listCode));
        return picturesMapper.selectFilesByListCodes(Page.of(page, size), query);
    }

    public IPage<Pictures> queryPages(Collection<String> listCodes,long page,long size){
        QueryFilesByListCodes query = new QueryFilesByListCodes();
        query.setListCodes(listCodes);
        return picturesMapper.selectFilesByListCodes(Page.of(page, size), query);
    }
}
