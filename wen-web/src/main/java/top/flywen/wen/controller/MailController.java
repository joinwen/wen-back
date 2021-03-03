package top.flywen.wen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.flywen.wen.mail.service.MailService;

@RequestMapping("/mail")
@RestController
public class MailController {
  @Autowired
  private MailService mailService;

  @GetMapping("/simple")
  public void sendSimpleMail(
    @RequestParam("to") String to,
    @RequestParam("subject") String subject,
    @RequestParam("content") String content
  ) {
    mailService.sendSimpleMail(to, subject, content);
  }
}
