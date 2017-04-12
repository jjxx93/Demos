package thrift;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

/**
 * 客户端
 * Created by jjxx9 on 2017/4/12.
 */
public class HelloClientDemo {
    private static final String SERVER_IP = "localhost";
    private static final int SERVER_PORT = 9090;
    private static final int TIME_OUT = 30000;

    public void start(String username) {
        TTransport transport = new TSocket(SERVER_IP, SERVER_PORT, TIME_OUT);       // 1.创建TTransport
        TProtocol protocol = new TBinaryProtocol(transport);                        // 2.创建TProtocol
        HelloWorldService.Client client = new HelloWorldService.Client(protocol);   // 3.创建Client
        try {
            transport.open();
            String result = client.sayHello(username);      // 4.调用client相关方法
            System.out.println("Receive message: " + result);
        } catch (TException e) {
            e.printStackTrace();
        } finally {
            transport.close();
        }
    }

    public static void main(String[] args) {
        HelloClientDemo client = new HelloClientDemo();
        client.start("Xiong Jiaxin");
    }
}
