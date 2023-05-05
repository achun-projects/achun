package site.achun.support.api.utils;

import java.util.Optional;
import java.util.function.Function;

/**
 * Description
 *
 * @Author: SunAo
 * @Date: 2021/3/19 14:55
 */
public class EnumUtil {

    public static <T extends Enum,V> Optional<T> parse(T[] enums, Function<T, V> function, V value){
        if(value!=null){
            for (T an : enums) {
                if(function.apply(an).equals(value)){
                    return Optional.of(an);
                }
            }
        }
        return Optional.empty();
    }

}
