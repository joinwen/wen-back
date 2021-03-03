package top.flywen.wen.mail.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import top.flywen.wen.config.GlobalConstant;
import top.flywen.wen.mail.service.MailService;

import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
public class MailServiceImpl implements MailService {
  @Autowired
  private JavaMailSender javaMailSender;

  @Override
  public void sendSimpleMail(String to, String subject, String content) {
    try {
      SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
      simpleMailMessage.setFrom(GlobalConstant.MAIL_FROM);
      simpleMailMessage.setTo(to);
      simpleMailMessage.setSubject(subject);
      simpleMailMessage.setText(content);
      javaMailSender.send(simpleMailMessage);
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  @Override
  public void sendHtmlMail(String to, String subject, String content) {
    MimeMessage mimeMessage = null;
    try {
      mimeMessage = javaMailSender.createMimeMessage();
      MimeMessageHelper helper = new MimeMessageHelper(mimeMessage , true);
      helper.setFrom(GlobalConstant.MAIL_FROM);
      helper.setTo(to);
      helper.setSubject(subject);
      helper.setText(content, true);
      javaMailSender.send(mimeMessage);
    } catch (Exception e) {

    }
  }

  @Override
  public void sendAttachmentsMail(String to, String subject, String content, String path) {
    MimeMessage mimeMessage = null;
    try {
      mimeMessage = javaMailSender.createMimeMessage();
      MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
      helper.setFrom(GlobalConstant.MAIL_FROM);
      helper.setTo(to);
      helper.setSubject(subject);
      helper.setText(content);
      FileSystemResource file = new FileSystemResource(new File(path));
      helper.addAttachment(GlobalConstant.ATTACHMENT_NAME, file);
      javaMailSender.send(mimeMessage);
    } catch (Exception e) {
    }
  }

  @Override
  public void sendAttachmentsMail(String to, String subject, String content, String attachmentName, String path) {
    MimeMessage mimeMessage = null;
    try {
      mimeMessage = javaMailSender.createMimeMessage();
      MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
      helper.setFrom(GlobalConstant.MAIL_FROM);
      helper.setTo(to);
      helper.setSubject(subject);
      helper.setText(content);
      FileSystemResource file = new FileSystemResource(new File(path));
      helper.addAttachment(attachmentName, file);
      javaMailSender.send(mimeMessage);
    } catch (Exception e) {
    }
  }

  @Override
  public void sendAttachmentsMail(String to, String subject, String content, File file) {
    MimeMessage mimeMessage = null;
    try {
      mimeMessage = javaMailSender.createMimeMessage();
      MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
      helper.setFrom(GlobalConstant.MAIL_FROM);
      helper.setTo(to);
      helper.setSubject(subject);
      helper.setText(content);
      helper.addAttachment(file.getName(), file);
    } catch (Exception e) {
    }
  }

  @Override
  public void sendAttachmentsMail(String to, String subject, String content, String attachmentName, File file) {
    MimeMessage mimeMessage = null;
    try {
      mimeMessage = javaMailSender.createMimeMessage();
      MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
      helper.setFrom(GlobalConstant.MAIL_FROM);
      helper.setTo(to);
      helper.setSubject(subject);
      helper.setText(content);
      helper.addAttachment(attachmentName, file);
    } catch (Exception e) {
    }
  }

  @Override
  public void sendAttachmentsMail(String to, String subject, String content, String attachmentName, InputStreamSource fis) {
    MimeMessage mimeMessage = null;
    try {
      mimeMessage = javaMailSender.createMimeMessage();
      MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
      helper.setFrom(GlobalConstant.MAIL_FROM);
      helper.setTo(to);
      helper.setSubject(subject);
      helper.setText(content);
      helper.addAttachment(attachmentName, fis);
    } catch (Exception e) {
    }
  }
}
