package top.flywen.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application-config.yml")

public class GlobalConstant {

  public static String USER_INFO;
  public static String MAIL_FROM;
  public static String ATTACHMENT_NAME;

  @Value("${wen.web.user_info}")
  public void setUserInfo(String userInfo) {
    GlobalConstant.USER_INFO = userInfo;
  }

  @Value("${wen.email.mail_from}")
  public void setMailFrom(String mailFrom) {
    GlobalConstant.MAIL_FROM = mailFrom;
  }

  @Value("${wen.email.attachment_name}")
  public void setAttachmentName(String attachmentName) {
    GlobalConstant.ATTACHMENT_NAME = attachmentName;
  }
}
