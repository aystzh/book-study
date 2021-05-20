package java8.tomcat;

import java.io.IOException;
import java.net.MulticastSocket;

/**
 * Created by zhanghuan on 2020/7/17.
 */

public class TomcatServerTest {


    public void test() throws IOException {
        //组播通讯
        MulticastSocket multicastSocket = new MulticastSocket(9999);

    }
}
