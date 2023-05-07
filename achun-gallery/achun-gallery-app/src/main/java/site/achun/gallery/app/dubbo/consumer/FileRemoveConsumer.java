package site.achun.gallery.app.dubbo.consumer;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Component;
import site.achun.file.api.modules.file.FileRemoveFacade;

import java.util.Collection;

@Component
public class FileRemoveConsumer {

    @DubboReference
    private FileRemoveFacade fileRemoveFacade;

    public void removeFiles(Collection<String> fileCodes){
        fileRemoveFacade.removeFile(fileCodes);
    }

    public void removeFile(String fileCode){
        fileRemoveFacade.removeFile(fileCode);
    }
}
