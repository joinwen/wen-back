package top.flywen.wen.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import top.flywen.wen.base.BaseUsernamePasswordToken;
import top.flywen.wen.business.AuthService;
import top.flywen.wen.entity.User;

public class BaseRealm extends AuthorizingRealm {

    private static final String USER_INFO  = "user_info";
    @Autowired
    private AuthService authService;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        BaseUsernamePasswordToken usernamePasswordToken = (BaseUsernamePasswordToken) authenticationToken;
        String username = usernamePasswordToken.getUsername();
        Integer isSys = usernamePasswordToken.getIsSys();
        User user = null;
        user = authService.findUserByName(username, isSys);
        if(user == null) {
            throw new UnknownAccountException();
        }
        ByteSource bytes = ByteSource.Util.bytes(user.getPassword());
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(user,user.getPassword(),bytes,getName());
        SecurityUtils.getSubject().getSession().setAttribute(USER_INFO, user);
        return simpleAuthenticationInfo;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }
}
