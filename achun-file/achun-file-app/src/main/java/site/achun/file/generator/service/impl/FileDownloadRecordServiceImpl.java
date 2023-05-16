package site.achun.file.generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import site.achun.file.generator.domain.FileDownloadRecord;
import site.achun.file.generator.service.FileDownloadRecordService;
import site.achun.file.generator.mapper.FileDownloadRecordMapper;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【file_download_record】的数据库操作Service实现
* @createDate 2023-05-16 14:35:30
*/
@Service
public class FileDownloadRecordServiceImpl extends ServiceImpl<FileDownloadRecordMapper, FileDownloadRecord>
    implements FileDownloadRecordService{

}




