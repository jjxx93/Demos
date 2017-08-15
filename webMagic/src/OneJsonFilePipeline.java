import com.alibaba.fastjson.JSON;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.utils.FilePersistentBase;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by xiongjiaxin on 2017/8/15.
 */
public class OneJsonFilePipeline implements Pipeline {
    private Logger logger = LoggerFactory.getLogger(getClass());

    private PrintWriter printWriter;

    public OneJsonFilePipeline(PrintWriter printWriter) {
        this.printWriter = printWriter;
    }

    @Override
    public void process(ResultItems resultItems, Task task) {
        try {
            printWriter.write(JSON.toJSONString(resultItems.getAll()) + '\n');
            printWriter.flush();
        } catch (Exception e) {
            logger.warn("write file error", e);
        }
    }
}
