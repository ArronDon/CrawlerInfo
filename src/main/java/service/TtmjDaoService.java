package service;

import domain.Episode;

import java.util.List;

/**
 * Created by arron on 2016/9/17.
 */
public interface TtmjDaoService {
    Episode findEpisodeById(int id);
    void addEpisode(Episode episode);
    void addEpisodes(List<Episode> episodeList);
}
