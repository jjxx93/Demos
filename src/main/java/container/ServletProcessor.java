package container;

import javax.servlet.Servlet;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandler;

/**
 * Created by jjxx9 on 2017/3/28.
 */
public class ServletProcessor {
    public void process(Request request, Response response) {
        String uri = request.getUri();  // 获取uri
        System.out.println(uri);
        String servletName = uri.substring(uri.lastIndexOf("/") + 1);
        System.out.println(servletName);
        URLClassLoader loader = null;

        try {
            URL[] urls = new URL[1];
            URLStreamHandler streamHandler = null;
            File classPath = new File(Constants.WEB_ROOT);

            String repository = (new URL("file", null, classPath.getCanonicalPath()
                    + File.separator)).toString();      // 仓库所在位置
            urls[0] = new URL(null, repository, streamHandler);
            loader = new URLClassLoader(urls);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Class myClass = null;
        try {
            myClass = loader.loadClass(servletName);    // 载入servlet类
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Servlet servlet;
        RequestFacade requestFacade = new RequestFacade(request);
        ResponseFacade responseFacade = new ResponseFacade(response);
        try {
            servlet = (Servlet)myClass.newInstance();   // 生成servlet对象
            servlet.service(requestFacade, responseFacade);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
