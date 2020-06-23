package java8.stream;

import com.google.common.base.Function;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.IntBinaryOperator;
import java.util.stream.Stream;

/**
 * Created by zhanghuan on 2020/6/23.
 */
public class StreamTest {

    @Test
    public void testLambda() {
        //lambda表达式
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("begin");
            }
        });

        Thread thread = new Thread(() -> {
            System.out.println("lambda");
        });

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(thread);
        executorService.submit(thread1);
        //方法引用
        Function<String, Integer> stringIntegerFunction = Integer::parseInt;
        stringIntegerFunction.apply("10");
        Comparator<Integer> compare = Integer::compare;
        int compare1 = compare.compare(1, 2);
        System.out.println("compare1" + compare1);
        IntBinaryOperator compare2 = Integer::compare;
        compare2.applyAsInt(1, 2);
    }

    @Test
    public void customFunctionTest() {
        String run = new KitStreamFunction<LocalDateTime, String, String>() {
            @Override
            public String run(LocalDateTime localDateTime, String s) {
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(s);
                return localDateTime.format(dateTimeFormatter);
            }
        }.run(LocalDateTime.now(), "yyyy-MM-dd HH:mm:ss");

        KitStreamFunction<LocalDateTime, String, String> function = (LocalDateTime dataTime, String s) -> {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(s);
            return dataTime.format(dateTimeFormatter);
        };
        String run1 = function.run(LocalDateTime.now(), "yyyy-MM-dd HH:mm:ss");

    }


    @Test
    public void testStreamForEachOrdered() {
        Stream<String> integerStream = Stream.of("s", "g", "h");
        integerStream.forEach(integer -> System.out.println(integer));//forEach之后stream就被消费了
        // java.lang.IllegalStateException: stream has already been operated upon or closed
        integerStream.forEachOrdered(integer -> System.out.println(integer));
    }
}
