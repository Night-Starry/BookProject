package dao;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import util.JdbcUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public abstract class BaseDao {
    private static final QueryRunner queryRunner = new QueryRunner();

    /**
     * 更新、删除、增加操作
     * @param sql sql语句
     * @param args 参数
     * @return 若失败，返回-1
     */
    public static int update(String sql, Object ... args){
        Connection connection = JdbcUtil.getConnection();
        try {
            return queryRunner.update(connection, sql, args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 查询单条记录
     * @param type 返回的对象类型
     * @param sql  sql语句
     * @param args 参数
     * @return 若失败，返回null
     * @param <T>  返回对象的泛型
     */
    public static <T> T queryOne(Class<T> type, String sql, Object ... args){
        Connection connection = JdbcUtil.getConnection();
        try {
            return queryRunner.query(connection, sql, new BeanHandler<T>(type), args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 查询多条记录
     * @param type 返回的对象类型
     * @param sql  sql语句
     * @param args 参数
     * @return 若失败，返回null
     * @param <T>  返回对象的泛型
     */
    public static <T> List<T> queryList(Class<T> type, String sql, Object ... args){
        Connection connection = JdbcUtil.getConnection();
        try {
            return queryRunner.query(connection, sql, new BeanListHandler<T>(type), args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 查询单个值
     * @param sql sql语句
     * @param args 参数
     * @return 若失败，返回null
     */
    public static Object queryValue(String sql, Object ... args){
        Connection connection = JdbcUtil.getConnection();
        try {
            return queryRunner.query(connection, sql, new ScalarHandler<>(), args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
