package service;

import domain.Episode;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by arron on 2016/9/17.
 */
@Service("ttmjHandlerService")
public class TtmeijuHandlerServiceImpl implements TtmeijuHandlerService {
    private static final Logger logger = Logger.getLogger(TtmeijuHandlerServiceImpl.class);

    public int getPagesAmount(String html) {
        Document document = Jsoup.parse(html);
        Element pageDiv = document.select("div.pages").first();
        logger.info(pageDiv.text());
        Elements pageLinks = pageDiv.select("a[href]");
        int count = 0;
        for (Element li : pageLinks
                ) {
            if (isNumberic(li.text())) {
                int page = Integer.parseInt(li.text());
                if (page > count)
                    count = page;
            }
            //logger.info("text:" + li.text() + " li.tag: " + li.tag());
        }
        return count;
    }

    public List<Episode> getEpisodeList(String html) {
        if(html==null||html.equals(""))
            return null;
        Document document=Jsoup.parse(html);
        Elements trs=document.select("tr.Scontent");
        //每个scontent包括7列
        //序号0 名字1 链接2 大小3 格式4 字幕5 讨论6
        List<Episode> episodeList=new ArrayList<Episode>();
        for (Element tr:trs
             ) {
            List<Element> tds=tr.select("td");
            String name=tds.get(1).text();
            String link=generateLinks(tds.get(2));
            String size=tds.get(3).text();
            String format=tds.get(4).text();
            Episode episode=new Episode();
            episode.setSeries_name(name);
            episode.setLinks(link);
            episode.setVideo_size(size);
            episode.setFormat(format);
            episodeList.add(episode);
            //logger.info("ttmjhandlerserviceimpl:"+episode.getName());
        }
        return episodeList;
    }

    //判断字符串是否为数字
    private boolean isNumberic(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(str).matches();
    }
    //处理链接td
    private String generateLinks(Element element){
        StringBuffer buffer=new StringBuffer();
        for (Element e:element.select("a[rel=\"nofollow\"]")
             ) {
            buffer.append(e.attr("href")).append(";");
        }
        return buffer.toString();
    }

}
