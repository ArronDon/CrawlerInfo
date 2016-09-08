package service;

import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Repository;

/**
 * Created by arron on 2016/9/8.
 */
@Repository("memcacheService")
@ImportResource("classpath:spring-memcached.xml")
public class MemCacheServiceImpl implements MemCacheService{
    private static final String DIANPING_INFO_SSM="Dianping_info";

    public void settoCache(String key, String value) {

    }

    public void getfromCache(String key) {

    }
}
