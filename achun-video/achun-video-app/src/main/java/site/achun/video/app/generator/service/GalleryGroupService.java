package site.achun.video.app.generator.service;

import cn.hutool.core.util.StrUtil;
import site.achun.video.app.generator.domain.GalleryGroup;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Collection;
import java.util.List;

/**
* @author Administrator
* @description 针对表【gallery_group】的数据库操作Service
* @createDate 2023-05-29 17:17:29
*/
public interface GalleryGroupService extends IService<GalleryGroup> {

    default GalleryGroup queryByCode(String groupCode){
        return this.lambdaQuery()
                .eq(GalleryGroup::getGroupCode,groupCode)
                .one();
    }

    default List<GalleryGroup> queryByCodes(Collection<String> groupCodes, String userCode){
        return this.lambdaQuery()
                .in(GalleryGroup::getGroupCode,groupCodes)
                .eq(GalleryGroup::getUserCode,userCode)
                .list();
    }

    default GalleryGroup queryByCode(String groupCode,String userCode){
        return this.lambdaQuery()
                .eq(GalleryGroup::getGroupCode,groupCode)
                .eq(StrUtil.isNotEmpty(userCode),GalleryGroup::getUserCode,userCode)
                .one();
    }

}
