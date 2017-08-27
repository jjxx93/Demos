package fxfuli;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.io.PrintWriter;
import java.util.Collection;
import java.util.Map;

/**
 * 保存信息到文件
 *
 * Created by jjxx93 on 2017/8/27.
 */
public class OneFilePipeline implements Pipeline {
    private Logger logger = LoggerFactory.getLogger(getClass());

    private PrintWriter printWriter;

    public OneFilePipeline(PrintWriter printWriter) {
        this.printWriter = printWriter;
    }

    @Override
    public void process(ResultItems resultItems, Task task) {
        try {
            Map<String, Object> fields = resultItems.getAll();
            Collection values = fields.values();
            for (Object value : values) {
                printWriter.write(value + "\n");
            }
            printWriter.flush();
        } catch (Exception e) {
            logger.warn("write file error", e);
        }
    }
}
