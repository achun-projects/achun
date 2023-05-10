package site.achun.updown.app.service.module.detected;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import site.achun.updown.client.module.detected.request.LocalDetectedStart;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Slf4j
@Service
public class LocalFileDetectedService {

    public void detected(LocalDetectedStart request) {
        LoopGetHaveFilePaths loopGet = new LoopGetHaveFilePaths();
        Path path = Path.of(request.getLocalPath());
        List<Path> haveFilePaths = loopGet.apply(path);
        haveFilePaths.stream().forEach(p -> System.out.println(p.toAbsolutePath()));
        log.info("LocalFileDetectedService detected");
    }

    private static class LoopGetHaveFilePaths implements Function<Path,List<Path>>{
        private List<Path> haveFilePaths = null;

        @Override
        public List<Path> apply(Path path) {
            haveFilePaths = new ArrayList<>();
            loopDirectory(path);
            return haveFilePaths;
        }

        private void loopDirectory(Path path){
            if(!Files.isDirectory(path)) return;
            try {
                if(Files.list(path).anyMatch(file->!Files.isDirectory(file))){
                    haveFilePaths.add(path);
                }
                Files.list(path).filter(Files::isDirectory).forEach(this::loopDirectory);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
