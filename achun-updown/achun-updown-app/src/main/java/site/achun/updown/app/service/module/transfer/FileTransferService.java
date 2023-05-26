package site.achun.updown.app.service.module.transfer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileTransferService {


    private final List<FileTransferStrategy> fileTransferStrategies;
    public void transfer(FileTransferInfo fileInfo){
        transfer(fileInfo,TransferType.ALL);
    }

    // 指定处理器
    public void transfer(FileTransferInfo fileTransferInfo, Collection<TransferType> types){
        // 匹配执行器，进行操作
        List<FileTransferStrategy> strategies = fileTransferStrategies.stream()
                .filter(handler -> types.contains(handler.transferType()))
                .collect(Collectors.toList());
        for (FileTransferStrategy strategy : strategies) {
            if(strategy.match(fileTransferInfo)){
                try{
                    strategy.handler(fileTransferInfo);
                }catch (Exception ex){
                    log.error("处理器发生异常",ex);
                    ex.printStackTrace();
                }
            }
        }
    }

}
