package top.flywen.business;

import top.flywen.asset.entity.User;

public interface AuthService {
    User findUserByName(String username, Integer isSys);

    User login(User user);

    User register(User user);
}
