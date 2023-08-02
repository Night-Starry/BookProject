package service;

import bean.User;

import java.util.List;

public interface UserService {
    /**
     * 注册
     * @param user 用户
     */
    void registerUser(User user);

    /**
     * 登录
     * @param user 用户
     * @return user
     */
    User login(User user);

    /**
     *检查用户名是否可用
     * @param name 用户名
     * @return 如果存在，返回true, 否则返回false
     */
    boolean existUser(String name);

    /**
     *注销用户信息
     * @param name 用户名
     * @return 如果删除失败，返回-1
     */
    int delUser(String name);

    List<User> searchUser(String name);
}
