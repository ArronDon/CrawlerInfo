package servlet;

import junit.framework.TestCase;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by arron on 2016/8/30.
 */
public class HtmlProcessServletTest extends TestCase {
    public void testGetCommentList() throws Exception {
        ReadFileServlet reader = new ReadFileServlet();
        String response = reader.readfromFileNIO(ReadFileServlet.class.getResource("/page.html").getPath());
        HtmlProcessServlet htmlProcessor = new HtmlProcessServlet();
        htmlProcessor.getCommentList(response);
    }

    public void testGetShopName() throws Exception {
        ReadFileServlet reader = new ReadFileServlet();
        String response = reader.readfromFile(ReadFileServlet.class.getResource("/page.html").getPath());
        HtmlProcessServlet htmlProcessor = new HtmlProcessServlet();
        htmlProcessor.setShopName(response);
    }

    public void testGetPageCount() throws Exception {
        ReadFileServlet reader = new ReadFileServlet();
        String response = reader.readfromFile(ReadFileServlet.class.getResource("/page.html").getPath());
        HtmlProcessServlet htmlProcessor = new HtmlProcessServlet();
        int count = htmlProcessor.getPageCount(response);

    }
}