package service;

import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * Created by arron on 2016/9/3.
 */
public interface BaseGetInfoMainService {
    List<String> setGetParams();
    Map<String, String> genereateHttpGetParamsList(List<String> args);
    Queue<String> setDownloadUrlQueue(String baseUrl, int count);
    void mainService();
}
