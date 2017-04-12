package thrift;

import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.server.TNonblockingServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TTransportException;

/**
 * NIO服务器
 * Created by jjxx9 on 2017/4/12.
 */
public class HelloNioServerDemo {
    private static final int SERVER_PORT = 9090;

    public void start() {
        System.out.println("HelloWorld HelloNioServerDemo starting ````");

        // 1.引入TProcessor
        TProcessor tProcessor = new thrift.HelloWorldService.Processor<thrift.HelloWorldService.Iface>(new HelloWorldImpl());

        try {
            TNonblockingServerSocket serverTransport = new TNonblockingServerSocket(SERVER_PORT); // 2.创建TServerTransport
            TNonblockingServer.Args tArgs = new TNonblockingServer.Args(serverTransport);
            tArgs.processor(tProcessor);
            tArgs.transportFactory(new TFramedTransport.Factory());
            tArgs.protocolFactory(new TCompactProtocol.Factory());           // 3.创建TProtocol

            TServer server = new TNonblockingServer(tArgs);                  // 4.创建TThreadPoolServer
            server.serve();                 // 5.启动server
        } catch (TTransportException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        HelloNioServerDemo server = new HelloNioServerDemo();
        server.start();
    }
}
