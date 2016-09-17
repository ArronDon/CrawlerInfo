package service;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by arron on 2016/9/2.
 */
@Service("httpClientService")
public class HttpClientServiceImpl implements HttpClientService {
    private static final Logger logger=Logger.getLogger(HttpClientServiceImpl.class);
    public HttpGet createGetRequest(String url, Map<String, String> params) {
        String reqest = null;


        //设置请求报文头
        logger.info("httpclientserviceimpl "+url);
        HttpGet get = new HttpGet(url);
        get.setHeader("Cookie", params.get("Cookie"));
        get.setHeader("Host", params.get("Host"));
        if(params.containsKey("Referer"))
            get.setHeader("Referer", params.get("Referer"));
        get.setHeader("User-Agent", params.get("User-Agent"));
        return get;
    }

    public String getGetResponse(HttpGet request) {
        String responseBody = null;
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
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
        }
        return responseBody;
    }
}
