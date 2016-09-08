package service;

/**
 * Created by arron on 2016/9/8.
 */
public interface MemCacheService {
    void settoCache(String key, String value);

    void getfromCache(String key);
}
