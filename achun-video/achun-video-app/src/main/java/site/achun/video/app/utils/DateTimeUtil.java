package site.achun.video.app.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * Description
 *
 * @Author: Heiffeng
 * @Date: 2022/2/23 16:27
 */
public class DateTimeUtil {

    public static String parse(Date date) {
        String shortstring = null;
        long now = Calendar.getInstance().getTimeInMillis();
        long deltime = (now - date.getTime())/1000;
        if(deltime > 365*24*60*60) {
            shortstring = (int)(deltime/(365*24*60*60)) + "年前";
        } else if(deltime > 24*60*60) {
            shortstring = (int)(deltime/(24*60*60)) + "天前";
        } else if(deltime > 60*60) {
            shortstring = (int)(deltime/(60*60)) + "小时前";
        } else if(deltime > 60) {
            shortstring = (int)(deltime/(60)) + "分前";
        } else if(deltime > 1) {
            shortstring = deltime + "秒前";
        } else {
            shortstring = "1秒前";
        }
        return shortstring;
    }

    public static String parse(LocalDateTime localDateTime){
        LocalDateTime now = LocalDateTime.now();
        if(now.plusDays(-3).isAfter(localDateTime)){
            return localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        }else{
            return parse(new Date(localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()));
        }
    }

    public static void main(String[] args) {
        LocalDateTime date = LocalDateTime.of(2022,5,13,14,13,9);
        System.out.println(parse(date));
//        String time = "2012-04-20 10:40:55";
//        System.out.println(getShortTime(time));
    }


}
