package servlet;

import junit.framework.TestCase;

/**
 * Created by arron on 2016/8/30.
 */
public class ReadFileServletTest extends TestCase {
    public void testReadfromFile() throws Exception {
        ReadFileServlet reader=new ReadFileServlet();
        reader.readfromFile(ReadFileServlet.class.getResource("/page.html").getPath());
    }

}