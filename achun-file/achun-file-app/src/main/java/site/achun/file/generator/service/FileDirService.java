package site.achun.file.generator.service;

import cn.hutool.core.collection.CollUtil;
import site.achun.file.generator.domain.FileDir;
import com.baomidou.mybatisplus.extension.service.IService;
import site.achun.support.api.enums.Deleted;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
* @author Administrator
* @description 针对表【file_dir】的数据库操作Service
* @createDate 2023-10-30 20:38:41
*/
public interface FileDirService extends IService<FileDir> {

    void batchReplaceInto(List<FileDir> fileDirList);

    default FileDir queryByCode(String dirCode){
        return this.lambdaQuery()
                .eq(FileDir::getDirCode,dirCode)
                .eq(FileDir::getDeleted, Deleted.NO.getStatus())
                .one();
    }

    default List<FileDir> queryByParentCode(String parentDirCode){
        return this.lambdaQuery()
                .eq(FileDir::getParentDirCode,parentDirCode)
                .eq(FileDir::getDeleted,Deleted.NO.getStatus())
                .list();
    }

    default List<FileDir> queryByParentCodes(List<String> parentDirCodes){
        return this.lambdaQuery()
                .in(FileDir::getParentDirCode,parentDirCodes)
                .eq(FileDir::getDeleted,Deleted.NO.getStatus())
                .list();
    }
    default List<FileDir> queryDeepSub(String parentDirCode){
        List<FileDir> response = new ArrayList<>();
        FileDir parent = queryByCode(parentDirCode);
        if(parent == null){
            return response;
        }
        response.add(parent);

        List<String> parentDirCodes = Arrays.asList(parentDirCode);
        recursionQuerySub(parentDirCodes,response);
        return response;
    }

    default void recursionQuerySub(List<String> parentDirCodes,List<FileDir> response){
        List<FileDir> list = queryByParentCodes(parentDirCodes);
        if(CollUtil.isEmpty(list)){
            return;
        }
        response.addAll(list);
        List<String> dirCodes = list.stream()
                .map(FileDir::getDirCode)
                .collect(Collectors.toList());
        recursionQuerySub(dirCodes, response);
    }
}
