package net.hzsec.config.shiro;

import com.auth0.jwt.exceptions.JWTVerificationException;
import lombok.extern.slf4j.Slf4j;
import net.hzsec.entity.system.User;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;

/**
 * @author xxh
 * @since 2019/10/29
 */
@Slf4j
public class JWTCredentialsMatcher implements CredentialsMatcher {
	
    @Override
    public boolean doCredentialsMatch(AuthenticationToken authenticationToken, AuthenticationInfo authenticationInfo) {
        String token = (String) authenticationToken.getCredentials();
        User user = (User)authenticationInfo.getPrincipals().getPrimaryPrincipal();
        try {
            return JWTUtil.verify(token,user.getUsername(),user.getPassword());
        } catch (JWTVerificationException e) {
            log.error("Token Error:{}", e.getMessage());
        }

        return false;
    }

}
