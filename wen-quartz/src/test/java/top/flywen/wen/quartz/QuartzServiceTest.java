package top.flywen.wen.quartz;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.LinkedHashMap;
import java.util.Map;

@SpringBootTest
public class QuartzServiceTest {
  @Autowired
  private QuartzService quartzService;
  @Test
  public void test() {
    Map<String, String> map = new LinkedHashMap<>();
    quartzService.addJob(Job.class, "job", "test","1 * * * * ?",map);
  }
}
