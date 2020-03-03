package aystzh.com.base.enums.interfaces;

import java.util.Arrays;
import java.util.Optional;

/**
 * 自定义公共父接口
 * Created by zhanghuan on 2019/6/3.
 */
public interface CodedEnum {
    int getCode();

    static <E extends Enum<?> & CodedEnum> Optional<E> codeOf(Class<E> enumClass, int code) {
        return Arrays.stream(enumClass.getEnumConstants()).filter(e -> e.getCode() == code).findAny();
    }
}
