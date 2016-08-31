package servlet;

import junit.framework.TestCase;

/**
 * Created by arron on 2016/8/31.
 */
public class ShopCommentServletTest extends TestCase {
    public void testGetAllComments() throws Exception {
        String url="https://www.dianping.com/shop/21616826/review_all";
        ShopCommentServlet shopCommentServlet=new ShopCommentServlet();
        shopCommentServlet.getAllComments(url);
    }

}