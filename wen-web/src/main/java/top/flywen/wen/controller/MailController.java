package top.flywen.wen.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.flywen.wen.mail.service.MailService;

@RequestMapping("/mail")
@RestController
@Api(value = "邮箱接口", tags = {"邮箱接口"})
public class MailController {
  @Autowired
  private MailService mailService;

  @GetMapping("/simple")
  @ApiOperation(value = "发送邮件-simple", notes = "发送邮件-simple", httpMethod = "GET")
  public void sendSimpleMail(
    @RequestParam("to") String to,
    @RequestParam("subject") String subject,
    @RequestParam("content") String content
  ) {
    mailService.sendSimpleMail(to, subject, content);
  }
}
