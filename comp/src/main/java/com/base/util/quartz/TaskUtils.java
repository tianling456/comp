package com.base.util.quartz;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerKey;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;


import com.base.util.spring.SpringUtil;
import com.comp.entities.Quartz;

/**
 * 项目名： 创建时间：2017-5-3 创建人：Aobo 类名：TaskUtils 所属包名：com.base.util.quartz 项目名称：comp
 * 类功能描述：
 */
public class TaskUtils {
	public final static Logger log = Logger.getLogger(TaskUtils.class);
	/**
	 * 通过反射调用scheduleJob中定义的方法
	 * 
	 * @param scheduleJob
	 */
	public static void invokMethod(Quartz quartz) {
		Object object = null;
		Class<?> clazz = null;
		// springId不为空先按springId查找bean
		if (StringUtils.isNotBlank(quartz.getSpringId())) {
			object = SpringUtil.getBean(quartz.getSpringId());
		} else if (StringUtils.isNotBlank(quartz.getBeanClass())) {
			try {
				clazz = Class.forName(quartz.getBeanClass());
				object = clazz.newInstance();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		if (object == null) {
			log.error("任务名称 = [" + quartz.getJobName()
					+ "]---------------未启动成功，请检查是否配置正确！！！");
			return;
		}
		clazz = object.getClass();
		Method method = null;
		try {
			method = clazz.getDeclaredMethod(quartz.getMethodName());
		} catch (NoSuchMethodException e) {
			log.error("任务名称 = [" + quartz.getJobName()
					+ "]---------------未启动成功，方法名设置错误！！！");
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		if (method != null) {
			try {
				method.invoke(object);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
	}
}
