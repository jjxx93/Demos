package fxfuli;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.Collection;
import java.util.Map;

/**
 * 边爬边下载
 *
 * Created by jjxx93 on 2017/8/27.
 */
public class PictureDownPipeline implements Pipeline {
    private Logger logger = LoggerFactory.getLogger(getClass());

    private String dirName;

    public PictureDownPipeline(String dirName) {
        this.dirName = dirName;
    }

    @Override
    public void process(ResultItems resultItems, Task task) {
        try {
            Map<String, Object> fields = resultItems.getAll();
            Collection values = fields.values();
            for (Object value : values) {
                String url = (String) value;
                byte[] btImg = ImgDowner.getImageFromNetByUrl(url);
                if (null != btImg && btImg.length > 0) {
                    String fileName = dirName + url.substring(url.lastIndexOf('/'));
                    ImgDowner.writeImageToDisk(btImg, fileName);
                    logger.info(fileName + "下载成功!");
                }else{
                    logger.warn("没有从该连接获得内容");
                }
            }
        } catch (Exception e) {
            logger.warn("write file error", e);
        }
    }
}
