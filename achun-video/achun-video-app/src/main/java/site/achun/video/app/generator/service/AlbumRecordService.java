package site.achun.video.app.generator.service;

import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import site.achun.video.app.generator.domain.AlbumRecord;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author Administrator
* @description 针对表【album_record】的数据库操作Service
* @createDate 2023-05-29 17:17:29
*/
public interface AlbumRecordService extends IService<AlbumRecord> {

    default AlbumRecord getBy(String albumCode,String setCode) {
        return this.lambdaQuery()
                .eq(AlbumRecord::getAlbumCode,albumCode)
                .eq(AlbumRecord::getSetCode,setCode)
                .one();
    }
    default AlbumRecord getBySetCode(String setCode) {
        return this.lambdaQuery()
                .eq(AlbumRecord::getSetCode,setCode)
                .one();
    }

    default List<AlbumRecord> getBy(String albumCode){
        return this.lambdaQuery()
                .eq(AlbumRecord::getAlbumCode,albumCode)
                .list();
    }

    default boolean deleteBy(String setCode){
        return this.lambdaUpdate()
                .eq(AlbumRecord::getSetCode,setCode)
                .remove();
    }

    default AlbumRecord randomQuery(List<String> albumCodes){
        Page<AlbumRecord> page = lambdaQuery()
                .in(AlbumRecord::getAlbumCode, albumCodes)
                .page(Page.of(1, 1));

        long random = RandomUtil.randomLong(page.getTotal());
        AlbumRecord randomRecord = lambdaQuery()
                .in(AlbumRecord::getAlbumCode, albumCodes)
                .page(Page.of(random, 1)).getRecords().get(0);
        return randomRecord;
    }
}
