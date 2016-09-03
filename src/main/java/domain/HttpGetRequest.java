package domain;

import java.io.Serializable;

/**
 * Created by arron on 2016/9/3.
 */
public class HttpGetRequest implements Serializable {
    private static final long serialVersionUID = 7546473772372326891L;
    private String cookie;
    private String host;
    private String referer;
    private String user_agent;

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getReferer() {
        return referer;
    }

    public void setReferer(String referer) {
        this.referer = referer;
    }

    public String getUser_agent() {
        return user_agent;
    }

    public void setUser_agent(String user_agent) {
        this.user_agent = user_agent;
    }
}
