package net.hzsec;

import net.hzsec.config.shiro.JWTUtil;
import net.hzsec.entity.system.Role;
import net.hzsec.entity.system.User;
import net.hzsec.service.IRoleService;
import net.hzsec.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FtyApplicationTest {


    @Autowired
    IRoleService roleService;

    @Autowired
    IUserService userService;

    @Test
    public void test() {
        String username = "admin";
        User user = userService.selectUserInfo(username);
        System.out.println(user);
        List<Role> roles = roleService.selectRoleByUserId((long) 6);
        System.out.println(roles);
    }
}
