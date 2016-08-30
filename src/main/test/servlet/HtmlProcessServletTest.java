package servlet;

import junit.framework.TestCase;

/**
 * Created by arron on 2016/8/30.
 */
public class HtmlProcessServletTest extends TestCase {
    public void testGetCommentList() throws Exception {
        ReadFileServlet reader = new ReadFileServlet();
        String response = reader.readfromFile(ReadFileServlet.class.getResource("/page.html").getPath());
        HtmlProcessServlet htmlProcessor = new HtmlProcessServlet();
        htmlProcessor.getCommentList(response);
    }

}