package service;

import bean.User;
import junit.framework.TestCase;
import service.impl.UserServiceImpl;

public class UserServiceTest extends TestCase {

    private final UserService userService = new UserServiceImpl();
    private final User user = new User("admin", "admin", "admin@qq.com");

    public void testRegisterUser() {
        userService.registerUser(user);
    }

    public void testLogin() {
        if(null != userService.login(user)){
            System.out.println("登录成功");
        } else {
            System.out.println("登录失败");
        }
    }

    public void testExistUser() {
        System.out.println(userService.existUser(user.getName()));
    }
}