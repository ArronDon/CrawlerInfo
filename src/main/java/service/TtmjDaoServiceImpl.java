package service;

import dao.TtmjDao;
import domain.Episode;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by arron on 2016/9/17.
 */
@Service("ttmjDaoService")
public class TtmjDaoServiceImpl implements TtmjDaoService{
    private static final Logger logger=Logger.getLogger(TtmjDaoServiceImpl.class);
    @Autowired
    private TtmjDao ttmjDao;
    public Episode findEpisodeById(int id) {
        return ttmjDao.findEpisodeById(id);
    }


    public void addEpisode(Episode episode) {
        ttmjDao.addEpisode(episode);
    }

    public void addEpisodes(List<Episode> episodeList) {
        //logger.info("addEpisodelist method:"+episodeList.size());
        if (episodeList.size()==0) {
            logger.info("addEpisodes list为空");
            return;
        }
        for (Episode e:episodeList
             ) {

            ttmjDao.addEpisode(e);
            logger.info(e.getSeries_name() + " " + e.getLinks() + " " + e.getVideo_size());
        }
    }
}
