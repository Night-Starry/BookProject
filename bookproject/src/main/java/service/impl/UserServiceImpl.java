package service.impl;

import bean.User;
import dao.UserDao;
import dao.impl.UserDaoImpl;
import service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {

    private final UserDao userDao = new UserDaoImpl();
    @Override
    public void registerUser(User user) {
        if(-1 == userDao.saveUser(user)){
            System.out.println("注册失败");
        }
    }

    @Override
    public User login(User user) {
        return userDao.queryByNameAndPsw(user.getName(), user.getPassword());
    }

    @Override
    public boolean existUser(String name) {
        return null != userDao.queryByName(name);
    }

    @Override
    public int delUser(String name) {
        return userDao.delUser(name);
    }

    @Override
    public List<User> searchUser(String name) {
        return userDao.searchUser(name);
    }
}
