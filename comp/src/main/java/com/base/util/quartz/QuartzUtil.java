package com.base.util.quartz;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.StdScheduler;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import com.base.util.spring.SpringUtil;
import com.comp.entities.Quartz;

/**
 *项目名：
 *创建时间：2017-5-3
 *创建人：Aobo
 *类名：QuartzUtil
 *所属包名：com.base.util.quartz
 *项目名称：comp
 *类功能描述：
 */
public class QuartzUtil {
	public static final Logger log = Logger.getLogger(QuartzUtil.class);
	private static StdScheduler scheduler = SpringUtil.getBean("scheduler", StdScheduler.class);
	
	public static void init() throws Exception {
          //这里从数据库中获取任务信息数据
//        List<Quartz> jobList = scheduleJobMapper.getAll();
//        for (Quartz quartz : jobList) {
//            addJob(quartz);
//        }
        System.out.println(scheduler);
    }
	/** 
     * 添加任务 
     *  
     * @param scheduleJob 
     * @throws SchedulerException 
     */  
    public static void addJob(Quartz quartz) throws SchedulerException {  
        if (quartz == null || !Quartz.STATUS_RUNNING.equals(quartz.getJobStatus())) {  
            return;  
        }  
//        StdScheduler scheduler = schedulerFactoryBean.getScheduler();  
        log.debug(scheduler + ".......................................................................................add");  
        TriggerKey triggerKey = TriggerKey.triggerKey(quartz.getJobName(), quartz.getJobGroup());  
        CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);  
        // 不存在，创建一个  
        if (null == trigger) {  
            Class clazz = Quartz.CONCURRENT_IS.equals(quartz.getIsConcurrent()) ? QuartzJobFactory.class : QuartzJobFactoryDisallowConcurrentExecution.class;  
            JobDetail jobDetail = JobBuilder.newJob(clazz).withIdentity(quartz.getJobName(), quartz.getJobGroup()).build();  
            jobDetail.getJobDataMap().put("scheduleJob", quartz);  
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(quartz.getCronExpression());  
            trigger = TriggerBuilder.newTrigger().withIdentity(quartz.getJobName(), quartz.getJobGroup()).withSchedule(scheduleBuilder).build();  
            scheduler.scheduleJob(jobDetail, trigger);
        } else {  
            // Trigger已存在，那么更新相应的定时设置  
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(quartz.getCronExpression());  
            // 按新的cronExpression表达式重新构建trigger  
            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();  
            // 按新的trigger重新设置job执行  
            scheduler.rescheduleJob(triggerKey, trigger);
        }  
    }  
    
    /**  
     * 获取所有计划中的任务列表  
     *   
     * @return  
     * @throws SchedulerException  
     */  
    public List<Quartz> getAllJob() throws SchedulerException {  
        GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();  
        Set<JobKey> jobKeys = scheduler.getJobKeys(matcher);  
        List<Quartz> jobList = new ArrayList<Quartz>();  
        for (JobKey jobKey : jobKeys) {  
            List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);  
            for (Trigger trigger : triggers) {  
         	   Quartz quartz = new Quartz();  
         	   quartz.setJobName(jobKey.getName());  
         	   quartz.setJobGroup(jobKey.getGroup());  
         	   quartz.setDescription("触发器:" + trigger.getKey());  
                Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());  
                quartz.setJobStatus(triggerState.name());  
                if (trigger instanceof CronTrigger) {  
                    CronTrigger cronTrigger = (CronTrigger) trigger;  
                    String cronExpression = cronTrigger.getCronExpression();  
                    quartz.setCronExpression(cronExpression);  
                }  
                jobList.add(quartz);  
            }  
        }  
        return jobList;  
    }  
  
    /** 
     * 所有正在运行的job 
     *  
     * @return 
     * @throws SchedulerException 
     */  
    public List<Quartz> getRunningJob() throws SchedulerException {  
        List<JobExecutionContext> executingJobs = scheduler.getCurrentlyExecutingJobs();  
        List<Quartz> jobList = new ArrayList<Quartz>(executingJobs.size());  
        for (JobExecutionContext executingJob : executingJobs) {  
     	   Quartz quartz = new Quartz();  
            JobDetail jobDetail = executingJob.getJobDetail();  
            JobKey jobKey = jobDetail.getKey();  
            Trigger trigger = executingJob.getTrigger();  
            quartz.setJobName(jobKey.getName());  
            quartz.setJobGroup(jobKey.getGroup());  
            quartz.setDescription("触发器:" + trigger.getKey());  
            Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());  
            quartz.setJobStatus(triggerState.name());  
            if (trigger instanceof CronTrigger) {  
                CronTrigger cronTrigger = (CronTrigger) trigger;  
                String cronExpression = cronTrigger.getCronExpression();  
                quartz.setCronExpression(cronExpression);  
            }  
            jobList.add(quartz);  
        }  
        return jobList;  
    }  
  
    /** 
     * 暂停一个job 
     *  
     * @param scheduleJob 
     * @throws SchedulerException 
     */  
    public void pauseJob(Quartz quartz) throws SchedulerException {  
        JobKey jobKey = JobKey.jobKey(quartz.getJobName(), quartz.getJobGroup());  
        scheduler.pauseJob(jobKey);  
    }  
  
    /** 
     * 恢复一个job 
     *  
     * @param scheduleJob 
     * @throws SchedulerException 
     */  
    public void resumeJob(Quartz quartz) throws SchedulerException {  
        JobKey jobKey = JobKey.jobKey(quartz.getJobName(), quartz.getJobGroup());  
        scheduler.resumeJob(jobKey);  
    }  
  
    /** 
     * 删除一个job 
     *  
     * @param scheduleJob 
     * @throws SchedulerException 
     */  
    public void deleteJob(Quartz quartz) throws SchedulerException {  
        JobKey jobKey = JobKey.jobKey(quartz.getJobName(), quartz.getJobGroup());  
        scheduler.deleteJob(jobKey);  
    }  
  
    /** 
     * 立即执行job 
     *  
     * @param scheduleJob 
     * @throws SchedulerException 
     */  
    public void runAJobNow(Quartz quartz) throws SchedulerException {  
        JobKey jobKey = JobKey.jobKey(quartz.getJobName(), quartz.getJobGroup());  
        scheduler.triggerJob(jobKey);
    }  
  
    /** 
     * 更新job时间表达式 
     *  
     * @param scheduleJob 
     * @throws SchedulerException 
     */  
    public void updateJobCron(Quartz quartz) throws SchedulerException {  
        TriggerKey triggerKey = TriggerKey.triggerKey(quartz.getJobName(), quartz.getJobGroup());  
        CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);  
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(quartz.getCronExpression());  
        trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();  
        scheduler.rescheduleJob(triggerKey, trigger); 
    }  
}

