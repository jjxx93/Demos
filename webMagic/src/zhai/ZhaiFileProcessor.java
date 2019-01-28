package zhai;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

import java.io.IOException;

/**
 * 一键爬虫并下载
 *
 * Created by jjxx93 on 2017/8/27.
 */
public class ZhaiFileProcessor implements PageProcessor {
    // 部分一：抓取网站的相关配置，包括编码、抓取间隔、重试次数等
    private Site site = Site.me().setRetryTimes(3).setSleepTime(100);

    @Override
    // process是定制爬虫逻辑的核心接口，在这里编写抽取逻辑
    public void process(Page page) {
        // 部分二：定义如何抽取页面信息，并保存下来
        page.putField("name", page.getHtml().xpath("//h1/text()").toString());
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) throws IOException {
        // 在此输入套图首页url即可
        String url = "https://96xx2019.com/luyilu/1575.html";

        Spider.create(new ZhaiFileProcessor())
                //从"https://github.com/code4craft"开始抓
                .addUrl(url)
                .addPipeline(new ConsolePipeline())
                .addPipeline(new ZhaiFileCreatePipeline(url))
                //开启5个线程抓取
                .thread(5)
                //启动爬虫
                .run();
    }
}
