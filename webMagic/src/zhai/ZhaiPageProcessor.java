package zhai;

import fxfuli.FxfuliPageProcessor;
import fxfuli.OneFilePipeline;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by jjxx93 on 2017/11/11.
 */
public class ZhaiPageProcessor implements PageProcessor {
    // 部分一：抓取网站的相关配置，包括编码、抓取间隔、重试次数等
    private Site site = Site.me().setRetryTimes(3).setSleepTime(100);
    private String url;

    public ZhaiPageProcessor(String url) {
        this.url = url;
    }

    private String getUrlID() {
        return url.substring(url.lastIndexOf('/') + 1, url.lastIndexOf('.'));
    }

    @Override
    // process是定制爬虫逻辑的核心接口，在这里编写抽取逻辑
    public void process(Page page) {
        // 部分二：定义如何抽取页面信息，并保存下来
        if (page.getHtml().xpath("//article/p/img").match()) {
            List<String> imgUrls = page.getHtml().xpath("//article/p/img/@src").all();
            for (int i = 0; i < imgUrls.size(); i++) {
                page.putField(i+"", imgUrls.get(i));
            }
        }

//        page.putField("imgUrl", page.getHtml().xpath("//article/p/a/img").all().toString());
//        if (page.getResultItems().get("name") == null) {
//            //skip this page
//            page.setSkip(true);
//        }
//        page.putField("readme", page.getHtml().xpath("//div[@id='readme']/tidyText()"));

        // 部分三：从页面发现后续的url地址来抓取
        page.addTargetRequests(page.getHtml().links().regex("(" + getUrlID() + "_*[0-9]{1,2}" + ".html)").all());
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) throws IOException {
//        FileWriter fileWriter = new FileWriter(new File("E:/test.json"));
        PrintWriter printWriter = new PrintWriter(new FileWriter(new File("E:\\Work\\work3\\src.txt")));

        Spider.create(new ZhaiPageProcessor("https://96xx2019.com/luyilu/3590.html"))
                //从"https://github.com/code4craft"开始抓
                .addUrl("https://96xx2019.com/luyilu/3590.html")
                .addPipeline(new ConsolePipeline())
                .addPipeline(new OneFilePipeline(printWriter))
                //开启5个线程抓取
                .thread(5)
                //启动爬虫
                .run();

        System.out.println("https://96xx2019.com/xiurenwang/3836.html".substring(32,36));
    }
}
