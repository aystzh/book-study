package java8;


import java.util.Collections;
import java.util.Comparator;

/**
 * created by zhanghuan on 2020/4/5.
 */
public class AutoCloseableTest implements AutoCloseable {

    public void autoCloseable() {
        System.out.println("testAutoCloseable run!");
    }

    @Override
    public void close() throws Exception {
        System.out.println("调用close方法");
    }


    public static void main(String args[]) {
        try (AutoCloseableTest autoCloseableTest = new AutoCloseableTest()) {
            autoCloseableTest.autoCloseable();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
