package site.achun.file.generator.service;

import site.achun.file.generator.domain.Storage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.HashMap;
import java.util.Map;

/**
* @author Administrator
* @description 针对表【storage】的数据库操作Service
* @createDate 2023-05-16 14:35:30
*/
public interface StorageService extends IService<Storage> {

    final static Map<String,Storage> storageMap = new HashMap<>();
    default Storage getStorage(String storageCode){
        if(storageMap.containsKey(storageCode)){
            return storageMap.get(storageCode);
        } else{
            Storage storage = getByCode(storageCode);
            storageMap.put(storageCode,storage);
            return storage;
        }
    }


    default Storage getByCode(String code){
        return this.lambdaQuery()
                .eq(Storage::getStorageCode, code)
                .one();
    }
}
