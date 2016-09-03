package service;

import org.apache.http.client.methods.HttpGet;

import java.util.Map;

/**
 * Created by arron on 2016/9/2.
 */
public interface HttpClientService {
    HttpGet createGetRequest(String url, Map<String,String> params);
    String getGetResponse(HttpGet get);
}
