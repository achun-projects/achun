package site.achun.video.app.generator.service;

import site.achun.video.app.generator.domain.PlayList;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author Administrator
* @description 针对表【play_list】的数据库操作Service
* @createDate 2023-06-29 18:54:59
*/
public interface PlayListService extends IService<PlayList> {
    default PlayList getByName(String name,String objectCode){
        return this.lambdaQuery()
                .eq(PlayList::getName,name)
                .eq(PlayList::getObjectCode,objectCode)
                .one();
    }

    default PlayList getByCode(String plistCode){
        return this.lambdaQuery()
                .eq(PlayList::getPlistCode,plistCode)
                .one();
    }

    default PlayList getByCode(String plistCode,String objectCode){
        return this.lambdaQuery()
                .eq(PlayList::getPlistCode,plistCode)
                .eq(PlayList::getObjectCode,objectCode)
                .one();
    }
}
