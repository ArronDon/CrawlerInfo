package dao;

import com.google.code.ssm.api.ParameterDataUpdateContent;
import com.google.code.ssm.api.ParameterValueKeyProvider;
import com.google.code.ssm.api.ReadThroughSingleCache;
import com.google.code.ssm.api.UpdateSingleCache;
import org.springframework.stereotype.Repository;

/**
 * Created by arron on 2016/9/8.
 */
@Repository
public class MemCacheDao {
    //void settoCache(String key, String value);
    @ReadThroughSingleCache(namespace = "dianping_info",expiration = 30000)
    public String getfromCache(String key)throws Exception{


        return null;
    }
    @UpdateSingleCache(namespace = "dianping_info",expiration = 30000)
    public void settoCache(@ParameterValueKeyProvider @ParameterDataUpdateContent String value){
        System.out.println("cached to ssm");
    }
}
