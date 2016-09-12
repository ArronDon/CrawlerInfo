package crawl;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.util.HashMap;

/**
 * Created by arron on 2016/8/30.
 */
public class HttpClientProcessor {
    //获取输入url的页面内容，以string形式返回
    public String getResponse(String url, HashMap<String,String> params) {
        String responseBody = null;
        RequestConfig globalConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.IGNORE_COOKIES).build();
        CloseableHttpClient httpClient = HttpClients.createDefault();
        RequestConfig localConfig = RequestConfig.copy(globalConfig).setCookieSpec(CookieSpecs.IGNORE_COOKIES).build();
        HttpGet request = new HttpGet(url);
        //request.setConfig(localConfig);
        //设置请求报文头

        request.setHeader("Cookie", params.get("Cookie"));
        request.setHeader("Host", params.get("Host"));
        request.setHeader("Referer", params.get("Referer"));
        request.setHeader("User-Agent", params.get("User-Agent"));
//        int maxConnectTime = 3;
//        int n = maxConnectTime;
//        while (n > 0) {
            try {
                CloseableHttpResponse response = httpClient.execute(request);
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    responseBody = EntityUtils.toString(entity);
                    return responseBody;
                } else {
                    //n--;
                }
            } catch (Exception e) {
                e.printStackTrace();
                //n--;
            }

//        }
        try {
            //httpClient.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseBody;
    }
}
