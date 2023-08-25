package site.achun.gallery.app.service.pictures;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import site.achun.gallery.app.generator.domain.FileSet;
import site.achun.gallery.app.generator.domain.Pictures;
import site.achun.gallery.app.generator.mapper.PicturesMapper;
import site.achun.gallery.app.generator.service.FileSetService;
import site.achun.gallery.app.generator.service.PicturesService;
import site.achun.gallery.app.utils.PageUtil;
import site.achun.gallery.client.module.pictures.request.QueryTimelinePage;
import site.achun.gallery.client.module.pictures.response.TimelineResponse;
import site.achun.support.api.response.RspPage;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class TimelineQueryService {

    private final PicturesMapper picturesMapper;
    private final PicturesService picturesService;
    private final PictureConvertService convertService;
    private final FileSetService fileSetService;

    // 为日期查询增加缓存逻辑,临时方案，没有支持多账号。
    // 后面可以使用file_group做查询，即可支持多账号。
    // 现在不做是因为不确定每个上传都有group记录
    private static RspPage<TimelineResponse> temp = null;
    public RspPage<TimelineResponse> query(QueryTimelinePage query){
        new Thread(()->{
            temp = queryPage(query);
        }).start();
        if(temp != null){
            return temp;
        }else{
            return queryPage(query);
        }
    }

    private RspPage<TimelineResponse> queryPage(QueryTimelinePage query){
        IPage<TimelineResponse> timelinePage = picturesMapper.selectTimelinePage(Page.of(query.getPage(), query.getSize()),query.getUserCode());
        // 填充内容
        timelinePage.getRecords().forEach(record->{
            List<FileSet> fileSets = fileSetService.lambdaQuery()
                    .ge(FileSet::getCtime, LocalDateTime.of(record.getTime(), LocalTime.MIN))
                    .le(FileSet::getCtime, LocalDateTime.of(record.getTime(), LocalTime.MAX))
                    .eq(FileSet::getUserCode, query.getUserCode())
                    .orderByDesc(FileSet::getCtime)
                    .last("limit 10")
                    .list();
            List<String> fileSetCodes = fileSets.stream()
                    .map(FileSet::getCode)
                    .collect(Collectors.toList());
            List<Pictures> pictures = picturesService.lambdaQuery()
                    .in(Pictures::getSetCode,fileSetCodes)
                    .orderByDesc(Pictures::getCtime)
                    .last("limit 10")
                    .list();
            record.setPictures(convertService.toResponse(pictures));
        });
        return PageUtil.parse(timelinePage,query);
    }
}
