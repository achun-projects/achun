package site.achun.gallery.app.service.album_record;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import site.achun.gallery.app.generator.domain.AlbumRecord;
import site.achun.gallery.app.generator.mapper.AlbumRecordMapper;
import site.achun.gallery.app.generator.service.AlbumRecordService;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class AlbumRecordUpdateExecute {

    private final AlbumRecordService albumRecordService;
    private final AlbumRecordMapper albumRecordMapper;

    public void replaceInfo(String albumCode,String unitCode){
        log.info("AlbumRecordUpdateExecute.replaceInfo({},{})",albumCode,unitCode);
        albumRecordMapper.replaceInto(toAlbumRecord(albumCode,unitCode));
    }
    public void createAlbumRecord(String albumCode, String fileSetCode){
        // 删除已存在的关联关系
        if(albumRecordService.getBySetCode(fileSetCode)!=null){
            albumRecordService.deleteBy(fileSetCode);
        }
        // 创建新的关联关系
        albumRecordService.save(toAlbumRecord(albumCode,fileSetCode));
    }
    public void createAlbumRecord(String albumCode, Collection<String> fileSetCodes){
        List<AlbumRecord> list = toList(albumCode, fileSetCodes).stream()
                .filter(rec->albumRecordService.getBy(rec.getAlbumCode(),rec.getSetCode())==null)
                        .collect(Collectors.toList());
        albumRecordService.saveBatch(list);
    }

    private List<AlbumRecord> toList(String albumCode, Collection<String> fileSetCodes){
        return fileSetCodes.stream()
                .map(fileSetCode -> toAlbumRecord(albumCode,fileSetCode))
                .collect(Collectors.toList());
    }

    private AlbumRecord toAlbumRecord(String albumCode, String unitCode){
        return AlbumRecord.builder()
                .ctime(LocalDateTime.now())
                .albumCode(albumCode)
                .setCode(unitCode)
                .build();
    }
}
