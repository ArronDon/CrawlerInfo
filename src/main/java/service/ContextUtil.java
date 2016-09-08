package service;

import com.sun.istack.internal.logging.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

/**
 * Created by arron on 2016/9/5.
 */
@Service
public class ContextUtil implements ApplicationContextAware {
    private static Logger logger= Logger.getLogger(ContextUtil.class);
    private static ApplicationContext context;

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        logger.info("context"+applicationContext.getDisplayName());
        ContextUtil.context = applicationContext;
    }

    public static ApplicationContext getContext() {
        return context;
    }

    public static Object getBean(String beanName) {
        return context.getBean(beanName);
    }
}
