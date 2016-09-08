package service;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by arron on 2016/9/2.
 */
@Service("httpClientService")
public class HttpClientServiceImpl implements HttpClientService {
    public HttpGet createGetRequest(String url, Map<String, String> params) {
        String reqest = null;


        //request.setConfig(localConfig);
        //设置请求报文头
        HttpGet get = new HttpGet(url);
        get.setHeader("Cookie", params.get("Cookie"));
        get.setHeader("Host", params.get("Host"));
        get.setHeader("Referer", params.get("Referer"));
        get.setHeader("User-Agent", params.get("User-Agent"));
        //System.out.println(get.toString());
        return get;
    }

    public String getGetResponse(HttpGet request) {
        String responseBody = null;
        //HttpGet get = new HttpGet(url);//createGetRequest(url, params);
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            CloseableHttpResponse response = httpClient.execute(request);
            HttpEntity entity = response.getEntity();
            //System.out.println("response"+response.toString().length());
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
        return responseBody;
    }
}
