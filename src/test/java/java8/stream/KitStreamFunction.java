package java8.stream;

/**
 * Created by zhanghuan on 2020/6/23.
 */
@FunctionalInterface
public interface KitStreamFunction<T,R,S> {
    R run(T t, S s);
}
