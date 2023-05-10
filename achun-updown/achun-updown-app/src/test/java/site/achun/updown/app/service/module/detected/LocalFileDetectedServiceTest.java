package site.achun.updown.app.service.module.detected;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

//@SpringBootTest
public class LocalFileDetectedServiceTest {

    @Test
    public void test() throws IOException {
        Path path = Path.of("Z:\\buckets-z\\hj8\\20230426");
        loopDir(path);

        for (Path haveFilePath : haveFilePaths) {
            System.out.println(haveFilePath.toAbsolutePath());
        }
    }

    private List<Path> haveFilePaths = new ArrayList<>();

    private void loopDir(Path path){
        if(Files.isDirectory(path)){
            try {
                if(Files.list(path).anyMatch(file->!Files.isDirectory(file))){
                    System.out.println(path.getFileName());
                    haveFilePaths.add(path);
                }
                Files.list(path).filter(Files::isDirectory).forEach(this::loopDir);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            System.out.println(path.getFileName());
        }
    }
}
