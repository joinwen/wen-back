package top.flywen.business.impl;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.flywen.asset.base.BaseUsernamePasswordToken;
import top.flywen.business.AuthService;
import top.flywen.asset.entity.User;
import top.flywen.mybatis_plus.mapper.AuthMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthMapper authMapper;
    @Override
    public User findUserByName(String username, Integer isSys) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("username", username);
        map.put("is_sys", isSys);
        List<User> list = authMapper.selectByMap(map);
        User user = list.get(0);
        return user;
    }

    @Override
    public User login(User user) {
        Subject subject = SecurityUtils.getSubject();
        BaseUsernamePasswordToken token = new BaseUsernamePasswordToken();
        token.setUsername(user.getUsername());
        token.setPassword(user.getPassword().toCharArray());
        token.setSys(user.getIsSys());
        try{
            subject.login(token);
            user = (User) SecurityUtils.getSubject().getPrincipal();
        }catch (Exception e) {
            SecurityUtils.getSubject().logout();
            user = null;
        }
        return user;
    }

    @Override
    public User register(User user) {
        String salt = BCrypt.gensalt(),
          hashpw = BCrypt.hashpw(user.getPassword(), salt);
        user.setPassword(hashpw);
        user.setSalt(salt);
        int insert = authMapper.insert(user);
        return user;
    }
}
