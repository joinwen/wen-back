package top.flywen.wen.tk.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.flywen.wen.entity.User;

import java.util.List;

@SpringBootTest
public class UserMapperTest {
  @Autowired
  private UserMapper userMapper;
  @Test
  public void test() {
    List<User> users = userMapper.selectAll();
    System.out.println(users.size());
  }
}
