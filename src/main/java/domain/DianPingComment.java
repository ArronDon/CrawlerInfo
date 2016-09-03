package domain;

import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * Created by arron on 2016/9/2.
 */
@Component//(value = "dianpingComment")
public class DianpingComment implements Serializable {
    private static final long serialVersionUID = -7759539829697323701L;
    private String taste;
    private String environment;
    private String service;
    private String username;
    private String comment_time = null;
    private String content = null;
    private String shop_name = null;
    private int id = 0;

    public String getTaste() {
        return taste;
    }

    public void setTaste(String taste) {
        this.taste = taste;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getComment_time() {
        return comment_time;
    }

    public void setComment_time(String comment_time) {
        this.comment_time = comment_time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
