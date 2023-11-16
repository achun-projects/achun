package site.achun.gallery.app.service.rules.handler;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.fastjson2.JSON;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import site.achun.gallery.app.generator.domain.Pictures;
import site.achun.gallery.app.service.list.ListRandomQueryService;
import site.achun.gallery.app.service.rules.QueryHandler;
import site.achun.gallery.client.module.rules.beans.FromLists;
import site.achun.gallery.client.module.rules.beans.RuleType;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class FromListsQueryHandler implements QueryHandler {

    private final ListRandomQueryService listRandomQueryService;

    @Override
    public boolean match(RuleType type) {
        return RuleType.FROM_LISTS.equals(type);
    }

    @Override
    public List<String> query(String rule) {
        List<FromLists> fromLists = JSON.parseArray(rule, FromLists.class);
        List<String> respList = new ArrayList<>();
        for (FromLists list : fromLists) {
            List<Pictures> files = listRandomQueryService.randomQuery(list.getValues(),list.getCount());
            if(CollUtil.isNotEmpty(files)){
                respList.addAll(files.stream().map(Pictures::getFileCode).collect(Collectors.toList()));
            }
        }
        return respList;
    }

}
