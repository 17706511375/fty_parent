package net.hzsec.web.system;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import net.hzsec.base.Constant;
import net.hzsec.base.PublicResultConstant;
import net.hzsec.config.shiro.JWTUtil;
import net.hzsec.entity.system.User;
import net.hzsec.service.IUserService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;

/**
 * @author xxh
 * @since 2019/10/22
 */
@RestController
@RequestMapping("/user")
@Api(value = "后台用户")
@Slf4j
public class UserController {
    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }




    @PostMapping("/insert")
    @ApiOperation(value = "添加用户")
    public ResponseEntity<String> insert(@Valid @RequestBody User user){

        String rePassword = BCrypt.hashpw(user.getPassword(),BCrypt.gensalt());
        user.setPassword(rePassword);
        user.setCreatedTime(new Date());
        if (userService.insert(user)){
            return ResponseEntity.ok().body("添加成功");
        }
        return ResponseEntity.badRequest().body("添加失败");
    }


    @GetMapping("/getUser/{userId}")
    @ApiOperation(value = "获取用户",notes = "根据ID获取用户",response = User.class)
    public ResponseEntity<User> getUser(@PathVariable int userId){
        User user = userService.selectOne(new EntityWrapper<User>().where("status=0 and user_id={0}",userId));
        return new ResponseEntity<>(user, HttpStatus.OK);
    }



    @GetMapping("/info")
    @ApiOperation(value = "获取用户信息",notes = "根据Token获取用户信息")
    public ResponseEntity<User> insert(HttpServletRequest request){
        String token = request.getHeader(Constant.DEFAULT_TOEKN);
        String username = JWTUtil.getUsername(token);
        User user = userService.selectUserInfo(username);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

}

