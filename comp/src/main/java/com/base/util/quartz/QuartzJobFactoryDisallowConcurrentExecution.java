package com.base.util.quartz;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;


import com.comp.entities.Quartz;

/**
 *项目名：
 *创建时间：2017-5-3
 *创建人：Aobo
 *类名：QuartzJobFactoryDisallowConcurrentExecution
 *所属包名：com.base.util.quartz
 *项目名称：comp
 *类功能描述：
 */
public class QuartzJobFactoryDisallowConcurrentExecution implements Job{
	public final Logger log = Logger.getLogger(QuartzJobFactoryDisallowConcurrentExecution.class);  
    @Override  
    public void execute(JobExecutionContext context) throws JobExecutionException {  
    	Quartz quartz = (Quartz) context.getMergedJobDataMap().get("scheduleJob");  
        TaskUtils.invokMethod(quartz);  
    }  
}

