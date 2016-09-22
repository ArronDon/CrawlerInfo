package domain;

import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * Created by arron on 2016/9/17.
 */
@Component
public class Episode implements Serializable{
    private static final long serialVersionUID = -3472711560882944100L;
    private String series_name;
    private String links;
    private String video_size;
    private String format;

    public String getSeries_name() {
        return series_name;
    }

    public void setSeries_name(String series_name) {
        this.series_name = series_name;
    }

    public String getLinks() {
        return links;
    }

    public void setLinks(String links) {
        this.links = links;
    }

    public String getVideo_size() {
        return video_size;
    }

    public void setVideo_size(String video_size) {
        this.video_size = video_size;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }
}
