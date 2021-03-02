package top.flywen.wen.business.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.flywen.wen.entity.User;
import top.flywen.wen.business.UserService;
import top.flywen.wen.mp.mapper.UserMapper;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User addUser(User user) {
        String hashpw = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setSalt(hashpw);
        userMapper.insert(user);
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        return userMapper.selectList(null);
    }

    @Override
    public IPage<User> getUsersByPage(IPage<User> pg, Map<String, Object> map) {
        return this.userMapper.selectUsersByPage(pg, map);
    }
}
