package top.flywen.wen.mail.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamSource;

import java.io.File;
import java.io.FileInputStream;

public interface MailService {
  public void sendSimpleMail(String to, String subject, String content);
  public void sendHtmlMail(String to, String subject, String content);
  public void sendAttachmentsMail(String to, String subject, String content, String path);
  public void sendAttachmentsMail(String to, String subject, String content, String attachmentName, String path);
  public void sendAttachmentsMail(String to, String subject, String content, File file);
  public void sendAttachmentsMail(String to, String subject, String content, String attachmentName, File file);
  public void sendAttachmentsMail(String to, String subject, String content, String attachmentName, InputStreamSource fis);
}
