package thrift;

import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TTransportException;

/**
 * 服务器线程池，使用阻塞式IO
 * Created by jjxx9 on 2017/4/12.
 */
public class HelloServerPoolDemo {
    private static final int SERVER_PORT = 9090;

    public void start() {
        System.out.println("HelloWorld TSimpleServerPool starting ````");

        // 1.引入TProcessor
        TProcessor tProcessor = new HelloWorldService.Processor<HelloWorldService.Iface>(new HelloWorldImpl());

        try {
            TServerSocket serverTransport = new TServerSocket(SERVER_PORT); // 2.创建TServerTransport
            TThreadPoolServer.Args tArgs = new TThreadPoolServer.Args(serverTransport);
            tArgs.processor(tProcessor);
            tArgs.protocolFactory(new TBinaryProtocol.Factory());           // 3.创建TProtocol

            TServer server = new TThreadPoolServer(tArgs);                  // 4.创建TThreadPoolServer
            server.serve();                 // 5.启动server
        } catch (TTransportException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        HelloServerPoolDemo serverPool = new HelloServerPoolDemo();
        serverPool.start();
    }
}
