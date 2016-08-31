package servlet;

import entity.Comment;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arron on 2016/8/29.
 */
public class HtmlProcessServlet {

    public List<Comment> getCommentList(String html) {

        Document doc = Jsoup.parse(html);
        Element commentlist = doc.select("div.comment-list").first();
        System.out.println(commentlist);
        Elements lilist = commentlist.select("li[data-id]");
        List<Comment> list = new ArrayList<Comment>();
        String shop_name = doc.select("div.info-name > h2").first().text();
        for (Element li : lilist) {
            Elements remarks = li.select("span.rst");
            String taste = remarks.get(0).text();
            String environment = remarks.get(1).text();
            String service = remarks.get(2).text();
            String name = li.select("p.name > a").first().text();
            String comment_txt = li.select("div.J_brief-cont").first().text();
            String time = li.select("span.time").text();
            //System.out.println(name + "-" + comment_txt);
            //System.out.println(li);
            Comment commentBean = new Comment();
            commentBean.setTaste(taste);
            commentBean.setEnvironment(environment);
            commentBean.setService(service);
            commentBean.setUsername(name);
            commentBean.setContent(comment_txt);
            commentBean.setComment_time(time);
            list.add(commentBean);
        }
        for (Comment comment :
                list) {
            comment.setShop_name(shop_name);
        }
        return list;
    }

    public void setShopName(String html) {
        Document doc = Jsoup.parse(html);
        String shopname = doc.select("div.info-name > h2").first().text();
        System.out.println(shopname);
    }

    public int getPageCount(String html) {
        Document doc = Jsoup.parse(html);
        Elements pageLinks = doc.select("a.PageLink");
        int count = 0;
        for (Element element :
                pageLinks) {
            int page = Integer.parseInt(element.text());
            if (page > count)
                count = page;

        }
        System.out.println(count);
        return count;
    }
}
