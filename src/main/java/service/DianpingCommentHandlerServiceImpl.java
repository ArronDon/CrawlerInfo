package service;

import domain.DianpingComment;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arron on 2016/9/2.
 */
@Service("dianpingCommentHandlerService")
public class DianpingCommentHandlerServiceImpl implements DianpingCommentHandlerService {
    private static final Logger logger = Logger.getLogger(DianpingCommentHandlerServiceImpl.class);
    //@Autowired//(name = "dianpingComment")
    //private DianpingComment dianpingComment;

    public int getPagesAmount(String body) {
        Document document = Jsoup.parse(body);
        Elements pageLinks = document.select("a.PageLink");
        int count = 0;
        for (Element num : pageLinks) {
            int page = Integer.parseInt(num.text());
            if (page > count)
                count = page;
        }
        return count;
    }


    public List<DianpingComment> getCommentList(String body) {
        if (body == null || body.equals("")) {
            //logger.dubug("文本为空");
            return null;
        }
        logger.info("DianpingCommentHandlerServiceImpl:"+ Thread.currentThread().getName());
        Document doc = Jsoup.parse(body);
        Element commentlist = doc.select("div.comment-list").first();
        Elements lilist = commentlist.select("li[data-id]");
        List<DianpingComment> list = new ArrayList<DianpingComment>();
        String shop_name = doc.select("div.info-name > h2").first().text();
        for (Element li : lilist
                ) {
            //获取评价的口味，环境，服务的评分
            Elements remarks = li.select("span.rst");
            String taste = remarks.get(0).text();
            String environment = remarks.get(1).text();
            String service = remarks.get(2).text();
            //获取评论者的名字
            String username = li.select("p.name > a").first().text();
            //获取评论内容
            String comment_txt = li.select("div.J_brief-cont").first().text();
            //获取评论时间
            String time = li.select("span.time").text();
            logger.info(Thread.currentThread().getName());
            logger.info("评论内容如下："+taste+" "+username+" "+comment_txt);
            DianpingComment dianpingComment=new DianpingComment();
            dianpingComment.setTaste(taste);
            dianpingComment.setEnvironment(environment);
            dianpingComment.setService(service);
            dianpingComment.setUsername(username);
            dianpingComment.setContent(comment_txt);
            dianpingComment.setComment_time(time);
            dianpingComment.setShop_name(shop_name);
//            logger.info(dianpingComment.getUsername() + ":" + dianpingComment.getContent() + "<>" + dianpingComment
//                    .getShop_name());
            list.add(dianpingComment);
            //dianpingComment=null;
        }


        return list;
    }
}
