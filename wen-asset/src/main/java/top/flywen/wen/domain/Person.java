package top.flywen.wen.domain;

import lombok.Data;

import java.util.List;
@Data
public class Person {
  private Integer id;
  private String name;
  private String gender;
  private String description;
  private List<String> hobbies;
}
