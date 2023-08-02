package dao;

import bean.User;
import dao.impl.UserDaoImpl;
import junit.framework.TestCase;

public class UserDaoTest extends TestCase {
    private final UserDao userDao = new UserDaoImpl();

    public void testQueryByName() {
        System.out.println(userDao.queryByName("admin"));
    }

    public void testSaveUser() {
        User user = new User(2, "admin2", "admin2", "admin2@qq.com");
        if(1 == userDao.saveUser(user)){
            System.out.println("保存成功");
        } else {
            System.out.println("保存失败");
        }
    }

    public void testQueryByNameAndPsw() {
        System.out.println(userDao.queryByNameAndPsw("admin", "admin"));
    }
}