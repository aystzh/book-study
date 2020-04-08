package java8;

import org.junit.Test;

import java.util.Optional;

/**
 * created by zhanghuan on 2020/4/8.
 */
public class OptionalTest {


    @Test
    public void testOptional() {
        Optional<Object> empty = Optional.empty();
        Optional  ofNullable = Optional.ofNullable("null");
        System.out.println(ofNullable.isPresent());
        Optional.of("ceshi").ifPresent(System.out::println);
        Optional.of("复杂操作").ifPresent((val)->{
            System.out.println(String.format("%s_%s","字符串拼接",val));
        });
    }
}
