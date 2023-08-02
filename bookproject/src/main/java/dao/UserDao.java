package dao;

import bean.User;

import java.util.List;

public interface UserDao {

    /**
     * 通过姓名查询用户
     * @param name 用户名
     * @return 如果不存在用户，返回null
     */
    User queryByName(String name);

    /**
     * 保存用户信息
     * @param user 用户
     * @return 如果返回-1，保存失败；返回1成功
     */
    int saveUser(User user);


    /**
     * 通过姓名和密码查询用户
     * @param name 用户名
     * @param password 密码
     * @return 如果不存在用户，返回null
     */
    User queryByNameAndPsw(String name, String password);

    /**
     * 删除用户信息
     * @param name
     * @return
     */
    int delUser(String name);

    List<User> searchUser(String name);
}
