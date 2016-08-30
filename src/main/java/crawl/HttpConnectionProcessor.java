package crawl;

import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by arron on 2016/8/30.
 */
public class HttpConnectionProcessor {
    private HttpURLConnection conn = null;

    private HttpConnectionProcessor(String url) {
        URL obj;
        try {
            obj = new URL(url);
            conn = (HttpURLConnection) obj.openConnection();
            //设置请求参数
            conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, " +
                    "like Gecko) Chrome/51.0.2704.79 Safari/537.36");
            conn.setRequestProperty("Accept-Language", "en-US,en;q=0.8,zh-CN;q=0.6,zh;q=0.4");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public HttpURLConnection getConnection() {
        return conn;
    }
}
