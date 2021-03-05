package top.flywen.wen.thymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {
  @GetMapping("/index")
  public String index(ModelMap map) {
    map.addAttribute("message","你好，cywen");
    return "flywen";
  }
}
