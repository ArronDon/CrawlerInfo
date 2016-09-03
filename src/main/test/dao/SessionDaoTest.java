package dao;

import junit.framework.TestCase;
import org.apache.ibatis.session.SqlSession;

import java.sql.Connection;

/**
 * Created by arron on 2016/9/1.
 */
public class SessionDaoTest extends TestCase {
    public void testGetSession() throws Exception {
        SessionDao sessionDao=new SessionDao();
        SqlSession session=sessionDao.getSession();
        try{
            int i=0;
            while(i++<20){
                Connection connection=session.getConnection();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}