package socket;

import java.io.*;
import java.net.Socket;

/**
 * Created by jjxx9 on 2017/3/28.
 */
public class ClientSocketTest {
    public static void main(String[] args) throws IOException, InterruptedException {
        Socket socket = new Socket("219.223.240.197", 8080);
        //OutputStream os = socket.getOutputStream();
        boolean autoFlush = true;
        PrintWriter out = new PrintWriter(socket.getOutputStream(), autoFlush);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        // send an HTTP request to the web server
        out.println("GET /index.jsp HTTP/1.1");
        out.println("HOST:219.223.240.197:8080");
        out.println("Connection:Close");
        out.println();

        // read the response
        boolean loop = true;
        StringBuffer sb = new StringBuffer(8096);
        while (loop) {
            if (in.ready()) {
                int i = 0;
                while (i != -1) {
                    i = in.read();
                    sb.append((char) i);
                }
                loop = false;
            }
            Thread.currentThread().sleep(50);
        }

        // display the response to the out console
        System.out.println(sb.toString());
        socket.close();
    }
}
