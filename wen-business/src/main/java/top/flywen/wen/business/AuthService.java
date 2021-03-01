package top.flywen.wen.business;

import top.flywen.wen.entity.User;

public interface AuthService {
    User findUserByName(String username, Integer isSys);

    User login(User user);

    User register(User user);
}
