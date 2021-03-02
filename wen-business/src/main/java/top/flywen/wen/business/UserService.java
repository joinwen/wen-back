package top.flywen.wen.business;

import com.baomidou.mybatisplus.core.metadata.IPage;
import top.flywen.wen.entity.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    User addUser(User user);
    List<User> getAllUsers();

    IPage<User> getUsersByPage(IPage<User> pg, Map<String, Object> map);
}
