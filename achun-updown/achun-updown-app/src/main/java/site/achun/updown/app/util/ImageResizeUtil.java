package site.achun.updown.app.util;

import org.imgscalr.Scalr;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @Author: SunAo
 * @Date: 2021/9/1 下午12:17
 */
public class ImageResizeUtil {

    public static void scalr(File sourceFile,File targetFile,int width){
        try {
            targetFile.mkdirs();
            BufferedImage image = ImageIO.read(sourceFile);
            BufferedImage target = Scalr.resize(image, width);
            ImageIO.write(target,"jpg",targetFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
