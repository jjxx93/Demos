package container;

import java.io.IOException;

/**
 * Created by jjxx9 on 2017/3/28.
 */
public class StaticResourceProcessor {
    public void process(Request request, Response response) {
        try {
            response.setRequest(request);
            response.sendStaticResource();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
