package thrift;

import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TTransportException;

/**
 * 服务端
 * Created by jjxx9 on 2017/4/12.
 */
public class HelloServerDemo {
    private static final int SERVER_PORT = 9090;

    public void start() {
        System.out.println("HelloWorld TSimpleServer starting ````");

        // 1.引入TProcessor
        TProcessor tProcessor = new HelloWorldService.Processor<HelloWorldService.Iface>(new HelloWorldImpl());

        try {
            TServerSocket serverTransport = new TServerSocket(SERVER_PORT); // 2.创建TServerTransport
            TServer.Args tArgs = new TServer.Args(serverTransport);
            tArgs.processor(tProcessor);
            tArgs.protocolFactory(new TBinaryProtocol.Factory());           // 3.创建TProtocol

            TServer server = new TSimpleServer(tArgs);                      // 4.创建TServer
            server.serve();                 // 5.启动server
        } catch (TTransportException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        HelloServerDemo server = new HelloServerDemo();
        server.start();
    }
}
