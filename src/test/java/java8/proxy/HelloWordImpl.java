package java8.proxy;

/**
 * Created by zhanghuan on 2020/6/11.
 */
public class HelloWordImpl implements HelloWord{
    @Override
    public void sayHello(String name) {
        System.out.println("name :"+name);
    }
}
