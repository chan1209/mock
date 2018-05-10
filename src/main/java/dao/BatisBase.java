package dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zejunweizj on 16/8/29.
 */
public class BatisBase {


    private String resource = "config/jdbcConfig.xml";

    public Object DBexecute(String spaceName, Object VO) {

        try {

            // 使用类加载器加载mybatis的配置文件（它也加载关联的映射文件）
            InputStream is = BatisBase.class.getClassLoader().getResourceAsStream(resource);
            // 构建sqlSession的工厂
            SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);

            SqlSession session = sessionFactory.openSession();

            VO = session.selectOne(spaceName, VO);
            session.commit();

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }
        return VO;
    }


    public List<Object> DBexecuteList(String spaceName, Object VO) {

        List<Object> listVO = new ArrayList<Object>();
        try {

            // 使用类加载器加载mybatis的配置文件（它也加载关联的映射文件）
            InputStream is = BatisBase.class.getClassLoader().getResourceAsStream(resource);
            // 构建sqlSession的工厂
            SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);

            SqlSession session = sessionFactory.openSession();


            listVO = session.selectList(spaceName, VO);

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }
        return listVO;
    }



    public int DBexecuteDelete(String spaceName, Object VO) {

        int countRows = -1;
        try {

            // 使用类加载器加载mybatis的配置文件（它也加载关联的映射文件）
            InputStream is = BatisBase.class.getClassLoader().getResourceAsStream(resource);
            // 构建sqlSession的工厂
            SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);

            SqlSession session = sessionFactory.openSession(true);

            countRows = session.delete(spaceName, VO);
            session.commit();

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }


        return countRows;
    }

    public int DBexecuteInsert(String spaceName, Object VO){


        int countRows = -1;
        try {

            // 使用类加载器加载mybatis的配置文件（它也加载关联的映射文件）
            InputStream is = BatisBase.class.getClassLoader().getResourceAsStream(resource);
            // 构建sqlSession的工厂
            SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);

            SqlSession session = sessionFactory.openSession(true);

            countRows = session.insert(spaceName, VO);
            session.commit();

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }


        return countRows;
    }
}
