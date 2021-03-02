package top.flywen.wen.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application-config.yml")

public class GlobalConstant {

  public static String USER_INFO;

  @Value("${wen.back.user_info}")
  public void setUserInfo(String userInfo) {
    GlobalConstant.USER_INFO = userInfo;
  }
}
