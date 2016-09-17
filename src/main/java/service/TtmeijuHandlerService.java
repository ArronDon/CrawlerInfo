package service;

import domain.Episode;

import java.util.List;

/**
 * Created by arron on 2016/9/17.
 */
public interface TtmeijuHandlerService {
    int getPagesAmount(String html);
    List<Episode> getEpisodeList(String html);
}
