package top.flywen.thymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import top.flywen.asset.domain.Person;

import java.util.Arrays;

@Controller
@RequestMapping("/home")
public class HomeController {
  @GetMapping("/index")
  public String index(ModelMap map) {
    Person p = new Person();
    p.setGender("男性");
    String [] hobbies = {"足球","篮球","排球"};
    p.setHobbies(Arrays.asList(hobbies));
    p.setName("flywen");
    p.setDescription("你好，flywen");
    map.addAttribute("message",p);
    return "flywen";
  }
}
