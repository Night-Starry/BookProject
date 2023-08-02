package util;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.druid.util.JdbcUtils;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcUtil {

    private static DruidDataSource dataSource;

    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

    static{
        try {
            Properties properties = new Properties();
            // 读取配置文件
            InputStream resourceAsStream = JdbcUtils.class.getClassLoader()
                    .getResourceAsStream("jdbc.properties");
            // 从流中加载数据
            properties.load(resourceAsStream);
            // 创建数据库连接池
            dataSource = (DruidDataSource)DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取JDBC连接
     * @return
     */
    public static Connection getConnection(){
        //从threadLocal获取连接
        Connection con = threadLocal.get();
        if(con == null){
            //连接为空，即第一次访问
            try {
                //获取连接
                con = dataSource.getConnection();
                //储存在threadLocal中，供以后的JDBC操作
                threadLocal.set(con);
                //设置手动管理事务
                con.setAutoCommit(false);
            } catch (Exception e){
                e.printStackTrace();
            }
        }

        return con;
    }


    /**
     * 释放资源
     * @param con
     */
    public static void close(Connection con){
        if(con != null){
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 提交事务并释放资源
     */
    public static void commitAndClose(){
        Connection con = threadLocal.get();
        if(con != null){
            try {
                con.commit();
            }catch (Exception e){
                e.printStackTrace();
            } finally {
                try {
                    con.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }

        //一定要执行，否则会出错，因为tomcat底层使用线程池
        threadLocal.remove();
    }

    /**
     * 回滚事务并释放资源
     */
    public static void rollbackAndClose(){
        Connection con = threadLocal.get();
        if(con != null){
            try {
                con.rollback();
            }catch (Exception e){
                e.printStackTrace();
            } finally {
                try {
                    con.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }

        //一定要执行，否则会出错，因为tomcat底层使用线程池
        threadLocal.remove();
    }
}
