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
    public List<entity.Comment> getCommentList(String html) {
        Document doc = Jsoup.parse(html);
        Element commentlist = doc.select("div.comment-list").first();
        //System.out.println(commentlist);
        Elements lilist = commentlist.select("li[data-id]");
        List<Comment> list = new ArrayList<Comment>();
        for (Element li : lilist) {
            Elements remarks = li.select("span.rst");
            String taste = remarks.get(0).text();
            String environment = remarks.get(1).text();
            String service = remarks.get(2).text();
            String name = li.select("p.name > a").first().text();
            String comment_txt = li.select("div.J_brief-cont").first().text();
            String time = li.select("span.time").text();
            System.out.println(name + "-" + time);
            //System.out.println(li);
            Comment commentBean = new Comment();
            commentBean.setTaste(taste);
            commentBean.setEnvironment(environment);
            commentBean.setService(service);
            commentBean.setName(name);
            commentBean.setComment(comment_txt);
            commentBean.setTime(time);
            list.add(commentBean);
        }
        return list;
    }

}
