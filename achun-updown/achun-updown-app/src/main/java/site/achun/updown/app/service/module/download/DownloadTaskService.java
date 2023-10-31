package site.achun.updown.app.service.module.download;

import cn.hutool.core.io.FileUtil;
import cn.hutool.crypto.digest.MD5;
import cn.hutool.http.HttpResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import site.achun.file.client.enums.Type;
import site.achun.file.client.module.download.constant.ResultStatus;
import site.achun.file.client.module.download.request.Result;
import site.achun.file.client.module.download.request.Task;
import site.achun.file.client.module.file.FileUpdateClient;
import site.achun.file.client.module.file.request.InitFileInfo;
import site.achun.file.client.module.file.response.InitFileInfoResponse;
import site.achun.file.client.module.storage.StorageQueryClient;
import site.achun.file.client.module.storage.request.ByStorageCode;
import site.achun.file.client.module.storage.response.StorageResponse;
import site.achun.support.api.exception.RspException;
import site.achun.updown.app.runner.DownloadTaskRunner;
import site.achun.updown.app.service.module.transfer.FileTransferInfo;
import site.achun.updown.app.service.module.transfer.FileTransferService;
import site.achun.updown.app.util.HttpUtilPlus;

import java.io.File;

@Slf4j
@Service
@RequiredArgsConstructor
public class DownloadTaskService {

    private final StorageQueryClient storageQueryClient;

    private final FileUpdateClient fileUpdateClient;

    private final FileTransferService fileTransferService;

    public void tryDownload(Task task){
        Result downloadRecord = null;
        try{
            // task 转换为DownloadRecord实体
            downloadRecord = transfer(task);
            // 下载AsynInfo实体
            FileTransferInfo fileTransferInfo = download(task, downloadRecord);
            // 对文件进行额外处理，比如：图片需要压缩，视频需要转码等
            // 判断是否需要执行
            if(needTransfer(downloadRecord)){
                fileTransferService.transfer(fileTransferInfo);
            }
        }catch (RspException ex){
            if(downloadRecord!=null){
                downloadRecord.setStatus(ResultStatus.ERROR);
                downloadRecord.setDescription(ex.getInfo());
                downloadRecord.setMark(ex.getMark());
            }
            ex.printStackTrace();
        }catch (Exception ex){
            if(downloadRecord!=null){
                downloadRecord.setStatus(ResultStatus.ERROR);
                downloadRecord.setDescription(ex.getMessage());
            }
            ex.printStackTrace();
        }
        finally {
            // 保存记录
            try{
//                downloadRecordConsumer.saveRecord(downloadRecord);
                DownloadTaskRunner.WAITING=false;
            }catch (Exception ex){
                log.error("远程RPC接口调用失败，休息5分钟后，再次尝试调用...",ex);
                ex.printStackTrace();
                DownloadTaskRunner.WAITING=true;
            }
        }
    }

    private FileTransferInfo download(Task task, Result result){
        // 判断文件是否存在，是否需要覆盖
        long begin = System.currentTimeMillis();
        FileTransferInfo fileTransferInfo = new FileTransferInfo();
        StorageResponse storage = storageQueryClient.queryStorage(ByStorageCode.builder().code(task.getStorage()).build()).tryGetData();
        result.setStorageLocalDir(storage.getPath());
        fileTransferInfo.setStorage(storage);

        // 判断是否已经存在本地文件，是否需要覆盖
        String localFilePath = storage.getPath() + task.getKey();
        File newFile = new File(localFilePath);
        fileTransferInfo.setFile(newFile);
        if(newFile.exists()){
            result.setExistsLocalFile(true);
            if(task.getOverride()){
                newFile.delete();
                log.info("exists file:{}, will be override",newFile.getAbsolutePath());
            }else{
                log.info("exists file:{},no override",newFile.getAbsolutePath());
                result.setStatus(ResultStatus.SUCCESS_NO_OVERRIDE);
                return fileTransferInfo;
            }
        }

        // 开始下载文件
        HttpResponse response = HttpUtilPlus.downloadFile(
                task.getUrl(),
                newFile,
                -1,
                null,
                null);
        result.setHttpStatus(response.getStatus());

        if(response.isOk()){
            // 记录日志
            InitFileInfoResponse fileResponse = fileUpdateClient.initFileInfoV2(transfer(newFile, task)).tryGetData();
            fileTransferInfo.setFileCode(fileResponse.getFileCode());
            fileTransferInfo.setInStoragePath(fileResponse.getInStoragePath());
            // 设置参数
            result.setFileCode(fileResponse.getFileCode());
            result.setFileSize(fileResponse.getSize());
            Long cost = System.currentTimeMillis() - begin;
            if(result.getExistsLocalFile()!=null && result.getExistsLocalFile())
                result.setStatus(ResultStatus.SUCCESS_OVERRIDE);
            else
                result.setStatus(ResultStatus.SUCCESS);
            result.setCostTime(cost.intValue());
            log.info("download:{},size:{},cost:{}",newFile.getAbsolutePath(),newFile.length(),cost);
        }else{
            log.error("download asynTask Error,httpStatus[{}],asynTask:{},response:{}",
                    response.getStatus(),task,response);
        }
        return fileTransferInfo;
    }

    private InitFileInfo transfer(File file, Task task){
        String suffix = FileUtil.getSuffix(file);
        String md5 = MD5.create().digestHex(file);
        InitFileInfo info = InitFileInfo.builder()
                .thirdId(task.getThirdId())
                .md5(md5)
                .storageCode(task.getStorage())
                .fileName(file.getName())
                .absolutePath(file.getAbsolutePath())
                .unitCode(task.getUnit())
                .size(file.length()/1024)
                .suffix(suffix)
                .type(Type.parse(suffix).getCode())
                .build();
        return info;
    }

    private Result transfer(Task task){
        return Result.builder()
                .override(task.getOverride())
                .storage(task.getStorage())
                .fileCode(task.getFileCode())
                .unit(task.getUnit())
                .filePath(task.getKey())
                .existsLocalFile(false)
                .url(task.getUrl())
                .build();
    }

    // 判断是否满足文件转换条件
    private boolean needTransfer(Result result){
        // 如果下载成功
        if(result.getStatus().equals(ResultStatus.SUCCESS)) return true;
        // 如果成功覆盖
        if(result.getStatus().equals(ResultStatus.SUCCESS_OVERRIDE)) return true;
        return false;
    }

}
