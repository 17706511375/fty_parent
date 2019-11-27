package net.hzsec.web.login;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import net.hzsec.base.Constant;
import net.hzsec.base.MessageConstant;
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
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * 用户登陆相关接口
 *
 * @author xxh
 * @since 2019/10/23
 */
@RestController
@RequestMapping("/login")
@Api(value = "用户登陆")
@Slf4j
public class LoginController {

    @PostMapping("/login")
    @ApiOperation(value = "用户登陆")
    public ResponseEntity<Void> login(@Valid @RequestBody User loginUser) {
        Subject subject = SecurityUtils.getSubject();
        try {
            UsernamePasswordToken token = new UsernamePasswordToken(loginUser.getUsername(), loginUser.getPassword());
            subject.login(token);
            User user = (User) subject.getPrincipal();
            String newToken = JWTUtil.sign(user.getUsername(),user.getPassword());
            return ResponseEntity.ok().header(Constant.DEFAULT_TOEKN,newToken).build();
        } catch (Exception e) {
            e.printStackTrace();
            log.warn("User {} login fail, Reason:{}", loginUser.getUsername(), e.getMessage());
            ResponseEntity.BodyBuilder builder = ResponseEntity.status(HttpStatus.NO_CONTENT);
            if (e instanceof IncorrectCredentialsException){
                return builder.header(PublicResultConstant.DEFAULT_MESSAGE_HEADER,MessageConstant.WRONG_PASSWORD).build();
            }else if (e instanceof UnknownAccountException){
                return builder.header(PublicResultConstant.DEFAULT_MESSAGE_HEADER,MessageConstant.ACCOUNT_NOT_FOUND).build();
            }else if (e instanceof AuthenticationException){
                return builder.header(PublicResultConstant.DEFAULT_MESSAGE_HEADER,e.getMessage()).build();
            }
        }
        return ResponseEntity.badRequest().header(PublicResultConstant.DEFAULT_MESSAGE_HEADER,"UNKNOWN PROBLEM").build();
    }


    @GetMapping("/logout")
    @ApiOperation(value = "用户登出",notes = "注销用户的登陆信息")
    public ResponseEntity<Void> logout(HttpServletRequest request){
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            subject.logout();
        }
        return ResponseEntity.ok().build();
    }
}
