package site.achun.updown.app.util.video;

import lombok.Data;
import lombok.ToString;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Description
 *
 * @Author: Heiffeng
 * @Date: 2022/3/19 20:30
 */
public class VideoUtil {

    private final static Integer CUT_FRAME = 3;

    public static void main(String[] args) throws IOException {
//        VideoInfo info = parse(new File("Y:\\足球宝贝一拳打哭你！ (P1. 横屏版).mp4"),null);
//        System.out.println(info);
        // 创建路径对象
        Path fullPath = Paths.get("/aaa/bbb/ccc/name.jpg");
        Path prefixPath = Paths.get("/aaa/bbb");

        // 使用 relativize 方法得到相对路径
        Path relativePath = prefixPath.relativize(fullPath);
        System.out.println(relativePath.toString());
    }

    public static VideoInfo parse(File file,Path storagePath) throws IOException {
        System.out.println("截取视频截图开始："+ System.currentTimeMillis());
        FFmpegFrameGrabber grabber = FFmpegFrameGrabber.createDefault(file);
        // 第一帧图片存储位置
        Path targetFilePath = Path.of(storagePath.toString(),"_cover",storagePath.relativize(file.toPath()).toString(),".cover.jpg");
        // 视频文件名
        System.out.println("视频路径是：" + file.getAbsolutePath());
        System.out.println("图片名称是：" + targetFilePath.toString());

        grabber.start();

        // 视频总帧数
        int videoLength = grabber.getLengthInFrames();
        // 过滤前几帧,避免出现全黑的图片,依自己情况而定(每循环一次取一帧)
        int frameNumber = (int) (videoLength * 0.05);
        grabber.setFrameNumber(frameNumber);
        Frame frame = grabber.grabImage();
        //视频旋转度
        String rotate = grabber.getVideoMetadata("rotate");
        Java2DFrameConverter converter = new Java2DFrameConverter();
        //绘制图片
        BufferedImage bi = converter.getBufferedImage(frame);
        if (rotate != null) {
            // 旋转图片
            bi = rotate(bi, Integer.parseInt(rotate));
        }
        //图片的类型
        String imageMat = "jpg";
        //创建文件
        File output = targetFilePath.toFile();
        ImageIO.write(bi, imageMat, output);

        //拼接Map信息
        VideoInfo info = new VideoInfo();
        info.setWidth(bi.getWidth());
        info.setHeight(bi.getHeight());
        long duration = grabber.getLengthInTime() / (1000 * 1000);
        info.setDuration(duration);
        info.setRotate(rotate==null ? "0" : rotate);
        info.setFormat(grabber.getFormat());
        info.setPath(output.getPath());
        info.setCoverPath(targetFilePath);
        grabber.stop();
        return info;
    }

    @Data
    @ToString
    public static class VideoInfo{
        private Integer width;
        private Integer height;
        private Long duration;
        private String rotate;
        private String format;
        private String path;
        private Path coverPath;
    }

    /**
     * @Description: 根据视频旋转度来调整图片
     * @param src
     * @param angel	视频旋转度
     * @return  BufferedImage
     */
    public static BufferedImage rotate(BufferedImage src, int angel) {
        int src_width = src.getWidth(null);
        int src_height = src.getHeight(null);
        int type = src.getColorModel().getTransparency();
        Rectangle rect_des = calcRotatedSize(new Rectangle(new Dimension(src_width, src_height)), angel);
        BufferedImage bi = new BufferedImage(rect_des.width, rect_des.height, type);
        Graphics2D g2 = bi.createGraphics();
        g2.translate((rect_des.width - src_width) / 2, (rect_des.height - src_height) / 2);
        g2.rotate(Math.toRadians(angel), src_width / 2, src_height / 2);
        g2.drawImage(src, 0, 0, null);
        g2.dispose();
        return bi;
    }

    /**
     * @Description: 计算图片旋转大小
     * @param src
     * @param angel
     * @return  Rectangle
     */
    public static Rectangle calcRotatedSize(Rectangle src, int angel) {
        if (angel >= 90) {
            if (angel / 90 % 2 == 1) {
                int temp = src.height;
                src.height = src.width;
                src.width = temp;
            }
            angel = angel % 90;
        }
        double r = Math.sqrt(src.height * src.height + src.width * src.width) / 2;
        double len = 2 * Math.sin(Math.toRadians(angel) / 2) * r;
        double angel_alpha = (Math.PI - Math.toRadians(angel)) / 2;
        double angel_dalta_width = Math.atan((double) src.height / src.width);
        double angel_dalta_height = Math.atan((double) src.width / src.height);
        int len_dalta_width = (int) (len * Math.cos(Math.PI - angel_alpha - angel_dalta_width));
        int len_dalta_height = (int) (len * Math.cos(Math.PI - angel_alpha - angel_dalta_height));
        int des_width = src.width + len_dalta_width * 2;
        int des_height = src.height + len_dalta_height * 2;
        return new java.awt.Rectangle(new Dimension(des_width, des_height));
    }


}