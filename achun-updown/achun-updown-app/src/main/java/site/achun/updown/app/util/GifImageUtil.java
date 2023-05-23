package site.achun.updown.app.util;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Gif图片工具类
 *
 * @Author: SunAo
 * @Date: 2022/2/13 15:33
 */
public class GifImageUtil {

    /**
     * 获取GIF图的帧数
     * @param data
     * @return
     * @throws IOException
     */
    public static Integer getFrameCount(File file) throws IOException {
        ImageReader imageReader = ImageIO.getImageReadersByFormatName("gif").next();
        try(
                FileInputStream fis = new FileInputStream(file);
                ImageInputStream iis = ImageIO.createImageInputStream(fis)
        ){
            imageReader.setInput(iis, false);
            Integer numImages = imageReader.getNumImages(true);
            return numImages;
        }
    }

}
