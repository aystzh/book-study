package java8.enums;

import org.junit.Test;

import java.lang.reflect.Constructor;

/**
 * 枚举类型 实现单例的最安全的方式
 * 反射安全
 * 序列化/反序列化安全
 * 写法简单
 * 没有一个更有信服力的原因不去使用枚举
 * Created by zhanghuan on 2020/6/12.
 */
public class MyEnumsTest {

    @Test
    public void test1() throws Exception {
        PizzaStatus pizzaStatus = PizzaStatus.DELIVERED;
        Constructor<PizzaStatus> constructor = PizzaStatus.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        PizzaStatus pizzaStatus1 = constructor.newInstance();
        System.out.println(pizzaStatus);
        System.out.println(pizzaStatus1);
        System.out.println(pizzaStatus == pizzaStatus1);
    }

    @Test
    public void test2() throws Exception {
        PizzaStatus pizzaStatus = PizzaStatus.DELIVERED;
        Constructor<PizzaStatus> constructor = PizzaStatus.class.getDeclaredConstructor(String.class, int.class);
        constructor.setAccessible(true);
        System.out.println("拿到了构造器" + constructor);
        PizzaStatus pizzaStatus1 = constructor.newInstance("testInstance", 1);
        System.out.println(pizzaStatus);
        System.out.println(pizzaStatus1);
        System.out.println(pizzaStatus == pizzaStatus1);
    }
}
