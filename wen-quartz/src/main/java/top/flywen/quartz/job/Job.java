package top.flywen.quartz.job;

import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class Job extends QuartzJobBean {
  @Override
  protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
    // 获取参数
    JobDataMap jobDataMap = jobExecutionContext.getJobDetail().getJobDataMap();
    // 业务逻辑 ...
    System.out.println("------springbootquartzonejob执行" + jobDataMap.get("name").toString() + "###############" + jobExecutionContext.getTrigger());
  }
}
