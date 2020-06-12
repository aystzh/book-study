package java8.proxy;

import java.lang.reflect.Proxy;

/**
 * Created by zhanghuan on 2020/6/11.
 */
public class ProxyTest {
    public static void main(String[] args) {
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        CustomInvocationHandler customInvocationHandler = new CustomInvocationHandler(new HelloWordImpl());
        HelloWord o = (HelloWord) Proxy.newProxyInstance(ProxyTest.class.getClassLoader(), new Class[]{HelloWord.class}, customInvocationHandler);
        o.sayHello("你好");

    }
}
