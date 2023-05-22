package site.achun.updown.app.service.module.transfer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.achun.file.client.module.file.response.InitFileInfoResponse;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileTransferService {


    public void transfer(InitFileInfoResponse fileInfo){
//        transfer(fileTransferInfo,TransferType.ALL);
    }

    // 指定处理器
//    public void transfer(FileTransferInfo fileTransferInfo, Collection<TransferType> handlerTypes){
//        // 匹配执行器，进行操作
//        List<FileTransferHandler> handlers = fileTransferHandlers.stream()
//                .filter(handler -> handlerTypes.contains(handler.transferType()))
//                .collect(Collectors.toList());
//        for (FileTransferHandler fileTransferHandler : handlers) {
//            if(fileTransferHandler.isHandler(fileTransferInfo)){
//                try{
//                    fileTransferHandler.handler(fileTransferInfo);
//                }catch (Exception ex){
//                    log.error("处理器发生异常",ex);
//                    ex.printStackTrace();
//                }
//            }
//        }
//    }

}
