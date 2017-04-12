package thrift;

import org.apache.thrift.TException;

/**
 * HelloWorldService实现类
 * Created by jjxx9 on 2017/4/12.
 */
public class HelloWorldImpl implements thrift.HelloWorldService.Iface {
    public HelloWorldImpl() {
    }

    @Override
    public String sayHello(String username) throws TException {
        return "Hi, " + username + " welcome!";
    }
}
