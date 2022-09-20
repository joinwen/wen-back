package top.flywen.mybatis_tk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("top.flywen.mybatis_tk.mapper")
public class TkApplication {
  public static void main(String[] args) {
    SpringApplication.run(TkApplication.class, args);
  }
}
