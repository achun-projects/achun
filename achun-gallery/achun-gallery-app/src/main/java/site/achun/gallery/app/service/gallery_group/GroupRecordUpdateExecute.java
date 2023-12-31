package site.achun.gallery.app.service.gallery_group;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import site.achun.gallery.app.generator.domain.GalleryGroupRecord;
import site.achun.gallery.app.generator.service.GalleryGroupRecordService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 分组记录更行执行器
 * <p>
 * Author: Heiffeng
 * Date: 2023/3/13
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class GroupRecordUpdateExecute {

    private final GalleryGroupRecordService groupRecordService;

    /**
     * 保存分组记录（批量）
     * @param groupCodes
     * @param listCode
     */
    public void createRecord(String groupCode, String listCode){
        if(StrUtil.isEmpty(groupCode)){
            log.info("相册分组集合为空。listCode:{}",listCode);
            return;
        }
        GalleryGroupRecord record = GalleryGroupRecord.builder()
                .groupCode(groupCode)
                .listCode(listCode)
                .ctime(LocalDateTime.now())
                .build();
        groupRecordService.save(record);
    }
}
