import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.util.logging.Logger;

/**
 * Created by jiax on 2016/12/6.
 */
public class ClientHandler extends ChannelHandlerAdapter {
    private static final Logger logger = Logger.getLogger(ClientHandler.class.getName());

    //    客户端和服务端TCP链路建立成功后，调用channelActive方法
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        String req = "Time" + System.getProperty("line.separator");     // 加换行符
        //byte[] req = "Time".getBytes();
        ByteBuf message;

        logger.info("Client: link success" + ctx.name());
        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                logger.warning("Client error");
            }
            message = Unpooled.copiedBuffer(req.getBytes());
            ctx.writeAndFlush(message);
        }

        //new KeyHandler(ctx).start();    // 启动按键处理线程
    }

    //    当服务器返回应答信息时调用
    @Override
    public void channelRead (ChannelHandlerContext ctx, Object msg) {
        String message = (String)msg;

        System.out.println(message);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        super.channelReadComplete(ctx);
//        System.out.println("Read Complete!");
    }

    @Override
    public void exceptionCaught (ChannelHandlerContext ctx, Throwable cause) {
        logger.warning("Unexpected exception from downstream : " + cause.getMessage());
        ctx.close();
    }
}
