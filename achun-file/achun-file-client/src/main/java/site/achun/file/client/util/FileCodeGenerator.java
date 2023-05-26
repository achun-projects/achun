package site.achun.file.client.util;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Description
 *
 * @Author: SunAo
 * @Date: 2021/7/26 16:26
 */
public class FileCodeGenerator {

    public static String machineCode18 = "01";

    public static Long make(){
        return Generator18.make();
    }
    static class Generator18{
        private final static AtomicInteger sub = new AtomicInteger(0);
        public static Long make(){
            StringBuffer sb = new StringBuffer(randomInt(1,9));
            StringBuffer reverseTimestamp = new StringBuffer(String.valueOf(System.currentTimeMillis() / 1000)).reverse();
            sb.append(reverseTimestamp);
            sb.append(machineCode18);
            int subValue = sub.incrementAndGet();
            if(subValue > 99999){
                sub.getAndSet(0);
                return make();
            }
            if(subValue<10){
                sb.append("0000");
            } else if(subValue<100){
                sb.append("000");
            } else if(subValue<1000){
                sb.append("00");
            } else if(subValue<10000){
                sb.append("0");
            }
            sb.append(subValue);
            return Long.parseLong(sb.toString());
        }
        private static Integer randomInt(int min,int max){
            return ThreadLocalRandom.current().nextInt(min, max);
        }
    }

}
