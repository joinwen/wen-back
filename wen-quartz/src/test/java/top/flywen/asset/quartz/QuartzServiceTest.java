package top.flywen.asset.quartz;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.flywen.quartz.job.Job;
import top.flywen.quartz.services.QuartzService;

import java.util.LinkedHashMap;
import java.util.Map;

public class QuartzServiceTest {
  @Autowired
  private QuartzService quartzService;
  public void test() {
    Map<String, String> map = new LinkedHashMap<>();
    quartzService.addJob(Job.class, "job", "test","1 * * * * ?",map);
  }
}
