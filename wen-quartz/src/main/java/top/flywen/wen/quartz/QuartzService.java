package top.flywen.wen.quartz;

import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
public class QuartzService {
  @Autowired
  private Scheduler scheduler;

  @PostConstruct
  public void startScheduler() {
    try {
      scheduler.start();
    } catch (SchedulerException e) {
      e.printStackTrace();
    }
  }

  public void addJob(Class<? extends QuartzJobBean> jobClass, String jobName, String jobGroupName, int jobTime,
                     int jobTimes, Map jobData) {
    try {
      // 任务名称和组构成任务key
      JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(jobName, jobGroupName)
        .build();
      // 设置job参数
      if(jobData!= null && jobData.size()>0){
        jobDetail.getJobDataMap().putAll(jobData);
      }
      // 使用simpleTrigger规则
      Trigger trigger = null;
      if (jobTimes < 0) {
        trigger = TriggerBuilder.newTrigger().withIdentity(jobName, jobGroupName)
          .withSchedule(SimpleScheduleBuilder.repeatSecondlyForever(1).withIntervalInSeconds(jobTime))
          .startNow().build();
      } else {
        trigger = TriggerBuilder
          .newTrigger().withIdentity(jobName, jobGroupName).withSchedule(SimpleScheduleBuilder
            .repeatSecondlyForever(1).withIntervalInSeconds(jobTime).withRepeatCount(jobTimes))
          .startNow().build();
      }
      scheduler.scheduleJob(jobDetail, trigger);
    } catch (SchedulerException e) {
      e.printStackTrace();
    }
  }

  public void addJob(Class<? extends QuartzJobBean> jobClass, String jobName, String jobGroupName, String jobTime, Map jobData) {
    try {
      // 创建jobDetail实例，绑定Job实现类
      // 指明job的名称，所在组的名称，以及绑定job类
      // 任务名称和组构成任务key
      JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(jobName, jobGroupName)
        .build();
      // 设置job参数
      if(jobData!= null && jobData.size()>0){
        jobDetail.getJobDataMap().putAll(jobData);
      }
      // 定义调度触发规则
      // 使用cornTrigger规则
      // 触发器key
      Trigger trigger = TriggerBuilder.newTrigger().withIdentity(jobName, jobGroupName)
        .startAt(DateBuilder.futureDate(1, DateBuilder.IntervalUnit.SECOND))
        .withSchedule(CronScheduleBuilder.cronSchedule(jobTime)).startNow().build();
      // 把作业和触发器注册到任务调度中
      scheduler.scheduleJob(jobDetail, trigger);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void updateJob(String jobName, String jobGroupName, String jobTime) {
    try {
      TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroupName);
      CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
      trigger = trigger.getTriggerBuilder().withIdentity(triggerKey)
        .withSchedule(CronScheduleBuilder.cronSchedule(jobTime)).build();
      // 重启触发器
      scheduler.rescheduleJob(triggerKey, trigger);
    } catch (SchedulerException e) {
      e.printStackTrace();
    }
  }

  public void deleteJob(String jobName, String jobGroupName) {
    try {
      scheduler.deleteJob(new JobKey(jobName, jobGroupName));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void pauseJob(String jobName, String jobGroupName) {
    try {
      JobKey jobKey = JobKey.jobKey(jobName, jobGroupName);
      scheduler.pauseJob(jobKey);
    } catch (SchedulerException e) {
      e.printStackTrace();
    }
  }

  public void resumeJob(String jobName, String jobGroupName) {
    try {
      JobKey jobKey = JobKey.jobKey(jobName, jobGroupName);
      scheduler.resumeJob(jobKey);
    } catch (SchedulerException e) {
      e.printStackTrace();
    }
  }

  public void runAJobNow(String jobName, String jobGroupName) {
    try {
      JobKey jobKey = JobKey.jobKey(jobName, jobGroupName);
      scheduler.triggerJob(jobKey);
    } catch (SchedulerException e) {
      e.printStackTrace();
    }
  }

  public List<Map<String, Object>> queryAllJob() {
    List<Map<String, Object>> jobList = null;
    try {
      GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
      Set<JobKey> jobKeys = scheduler.getJobKeys(matcher);
      jobList = new ArrayList<Map<String, Object>>();
      for (JobKey jobKey : jobKeys) {
        List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
        for (Trigger trigger : triggers) {
          Map<String, Object> map = new HashMap<>();
          map.put("jobName", jobKey.getName());
          map.put("jobGroupName", jobKey.getGroup());
          map.put("description", "触发器:" + trigger.getKey());
          Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
          map.put("jobStatus", triggerState.name());
          if (trigger instanceof CronTrigger) {
            CronTrigger cronTrigger = (CronTrigger) trigger;
            String cronExpression = cronTrigger.getCronExpression();
            map.put("jobTime", cronExpression);
          }
          jobList.add(map);
        }
      }
    } catch (SchedulerException e) {
      e.printStackTrace();
    }
    return jobList;
  }

  public List<Map<String, Object>> queryRunJob() {
    List<Map<String, Object>> jobList = null;
    try {
      List<JobExecutionContext> executingJobs = scheduler.getCurrentlyExecutingJobs();
      jobList = new ArrayList<Map<String, Object>>(executingJobs.size());
      for (JobExecutionContext executingJob : executingJobs) {
        Map<String, Object> map = new HashMap<String, Object>();
        JobDetail jobDetail = executingJob.getJobDetail();
        JobKey jobKey = jobDetail.getKey();
        Trigger trigger = executingJob.getTrigger();
        map.put("jobName", jobKey.getName());
        map.put("jobGroupName", jobKey.getGroup());
        map.put("description", "触发器:" + trigger.getKey());
        Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
        map.put("jobStatus", triggerState.name());
        if (trigger instanceof CronTrigger) {
          CronTrigger cronTrigger = (CronTrigger) trigger;
          String cronExpression = cronTrigger.getCronExpression();
          map.put("jobTime", cronExpression);
        }
        jobList.add(map);
      }
    } catch (SchedulerException e) {
      e.printStackTrace();
    }
    return jobList;
  }
}