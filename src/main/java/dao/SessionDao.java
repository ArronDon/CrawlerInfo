package dao;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.Reader;

/**
 * Created by arron on 2016/8/30.
 */
public class SessionDao {
    public SqlSession getSession(){
        SqlSession session = null;
        SqlSessionFactory sessionFactory;
        try {
            Reader reader = Resources.getResourceAsReader("mybatis-conf.xml");
            sessionFactory = new SqlSessionFactoryBuilder().build(reader);

            session = sessionFactory.openSession();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return session;
    }
}
