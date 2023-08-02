package dao.impl;

import bean.User;
import dao.BaseDao;
import dao.UserDao;

import java.util.List;

public class UserDaoImpl extends BaseDao implements UserDao {
    @Override
    public User queryByName(String name) {
        String sql = "Select * from user where name = ?";
        return queryOne(User.class, sql, name);
    }

    @Override
    public int saveUser(User user) {
        String sql = "insert into user(name, password, email) values(?, ?, ?)";
        return update(sql, user.getName(), user.getPassword(), user.getEmail());
    }

    @Override
    public User queryByNameAndPsw(String name, String password) {
        String sql = "Select * from user where name = ? and password = ?";
        return queryOne(User.class, sql, name, password);
    }

    @Override
    public int delUser(String name) {
        String sql = "delete from user where name = ?";
        return update(sql, name);
    }

    @Override
    public List<User> searchUser(String name) {
        String sql = "select * from user";
        if(name != null){
            sql += " where name like '%" + name + "%'";
        }
        return queryList(User.class, sql);
    }
}
