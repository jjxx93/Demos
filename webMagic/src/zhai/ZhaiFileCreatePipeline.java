package zhai;

import fxfuli.OneFilePipeline;
import fxfuli.PictureDownPipeline;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

/**
 * Created by jjxx93 on 2017/11/11.
 */
public class ZhaiFileCreatePipeline implements Pipeline {
    private Logger logger = LoggerFactory.getLogger(getClass());

    private String url;

    public ZhaiFileCreatePipeline(String url) {
        this.url = url;
    }

    @Override
    public void process(ResultItems resultItems, Task task) {
        try {
            String name = resultItems.get("name");
            String dirName = "E:\\Work\\" + name;
            File file = new File(dirName);

            if (file.mkdirs()) {
                System.out.println("文件夹创建成功！创建后的文件目录为：" + file.getPath());

                PrintWriter printWriter = new PrintWriter(new FileWriter(new File(dirName + "\\src.txt")));

                Spider.create(new ZhaiPageProcessor(url))
                        //从"https://github.com/code4craft"开始抓
                        .addUrl(url)
                        .addPipeline(new ConsolePipeline())
                        .addPipeline(new OneFilePipeline(printWriter))
                        .addPipeline(new PictureDownPipeline(dirName))
                        //开启5个线程抓取
                        .thread(5)
                        //启动爬虫
                        .run();
            }
        } catch (Exception e) {
            logger.warn("write file error", e);
        }
    }
}