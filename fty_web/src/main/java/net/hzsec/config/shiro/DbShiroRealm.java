package net.hzsec.config.shiro;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import lombok.extern.slf4j.Slf4j;
import net.hzsec.base.MessageConstant;
import net.hzsec.base.PublicResultConstant;
import net.hzsec.entity.system.User;
import net.hzsec.service.IUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;

@Slf4j
public class DbShiroRealm extends AuthorizingRealm {
    @Autowired
    private IUserService userService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof UsernamePasswordToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        User user = (User) principals.getPrimaryPrincipal();
        /*List<String> roles = user.getRoles();
        if(roles == null) {
            roles = userService.getUserRoles(user.getUserId());
            user.setRoles(roles);
        }
        if (roles != null)
            simpleAuthorizationInfo.addRoles(roles);*/

        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        //获取用户账号
        String username = token.getPrincipal().toString();

        User user = userService.selectOne(new EntityWrapper<User>().where("username={0}",username));
        if (!ObjectUtils.isEmpty(user)) {

            /*
             * 四个参数
             * principal：认证的实体信息，可以是username，也可以是数据库表对应的用户的实体对象
             * credentials：数据库中的密码（经过加密的密码）
             * credentialsSalt：盐值（使用用户名）
             * realmName：当前realm对象的name，调用父类的getName()方法即可
             */
            if(user.getStatus() == 1 ){
                //账户被封禁
                throw new AuthenticationException(MessageConstant.BLOCKED_ACCOUNT);
            }
            String realmName = getName();
            String credentials = user.getPassword();
            ByteSource credentialsSalt = ByteSource.Util.bytes(username);
            return new SimpleAuthenticationInfo(user, credentials, credentialsSalt, realmName);
        }
        throw new UnknownAccountException(MessageConstant.ACCOUNT_NOT_FOUND);
    }
}
