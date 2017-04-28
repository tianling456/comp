package com.base.util.activiti;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.zip.ZipInputStream;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.io.FileUtils;

import com.base.util.spring.SpringUtil;

/**
 *项目名：
 *创建时间：2017-4-1
 *创建人：Aobo
 *类名：ActivitiUtils
 *所属包名：com.base.util.activiti
 *项目名称：comp
 *类功能描述：
 */
public class ActivitiUtils {
	private static ProcessEngine processEngine;
	/**
	 * 创建流程引擎对象
	 * @param activitiConfigXml
	 * @return
	 * @throws Exception
	 */
	public static ProcessEngine getProcessEngine() throws Exception{
		processEngine = SpringUtil.getBean("processEngine", ProcessEngine.class);
		System.out.println(processEngine.getName());
		return processEngine;
	}
	/**
	 * 使用图片和bpmn起动流程
	 * @param bpmn   流程图的xml文件
	 * @param png    流程图的png图片
	 * @throws Exception
	 */
	public static void deploy(String bpmn,String png) throws Exception{
		Deployment deployment = getProcessEngine()
				.getRepositoryService()
				.createDeployment()
				.addClasspathResource(bpmn)
				.addClasspathResource(png)
				.deploy();
		System.out.println(deployment.getName());
		System.out.println(deployment.getId());
	}
	/**
	 * 使用zip起动流程
	 * @param zipFile zip文件路径
	 * @throws Exception
	 */
	public static void deploy(String zipFile) throws Exception{
		InputStream is = new FileInputStream(zipFile);
		ZipInputStream zip = new ZipInputStream(is);
		Deployment deployment = getProcessEngine()
				.getRepositoryService()
				.createDeployment()
				.addZipInputStream(zip)
				.deploy();
		System.out.println(deployment.getName());
		System.out.println(deployment.getId());
	}
	/**
	 * 启动流程
	 * @param processName 流程名称
	 */
	public static void startProcess(String processName) throws Exception{
		ProcessInstance instance = getProcessEngine()
				.getRuntimeService()
				.startProcessInstanceByKey(processName);
		System.out.println(instance.getActivityId()+""+instance.getBusinessKey());
	}
	
	/**
	 * 分页查看流程定义
	 * @param firstResult  起始位置
	 * @param maxResults   结束位置
	 * @return
	 * @throws Exception
	 */
	public static List<ProcessDefinition> getProcessDefinitionForList(int firstResult,int maxResults) throws Exception{
		List<ProcessDefinition> pdList = getProcessEngine()
				.getRepositoryService()
				.createProcessDefinitionQuery()
				.orderByProcessDefinitionVersion()
				.asc()
				.listPage(firstResult, maxResults);
		return pdList;
	}
	/**
	 * 查看流程定义
	 * @return
	 * @throws Exception
	 */
	public static List<ProcessDefinition> getProcessDefinitionForList(String processName) throws Exception{
		List<ProcessDefinition> pdList = getProcessEngine()
				.getRepositoryService()
				.createProcessDefinitionQuery()
				.processDefinitionKey(processName)
				.orderByProcessDefinitionVersion()
				.asc()
				.list();
		return pdList;
	}
	
	/**
	 * 查看流程定义
	 * @return
	 * @throws Exception
	 */
	public static List<ProcessDefinition> getProcessDefinitionForList() throws Exception{
		List<ProcessDefinition> pdList = getProcessEngine()
				.getRepositoryService()
				.createProcessDefinitionQuery()
				.orderByProcessDefinitionVersion()
				.asc()
				.list();
		return pdList;
	}
	
	/**
	 * 查看流程定义
	 * @param deploymentId  deploymentId
	 * @return
	 * @throws Exception
	 */
	public static List<ProcessDefinition> getProcessDefinitionById(String deploymentId) throws Exception{
		List<ProcessDefinition> pdList = getProcessEngine()
				.getRepositoryService()
				.createProcessDefinitionQuery()
				.deploymentId(deploymentId)
				.orderByProcessDefinitionVersion()
				.asc()
				.list();
		return pdList;
	}
	
	/**
	 * 查看流程定义
	 * @param deploymentId  deploymentId
	 * @return
	 * @throws Exception
	 */
	public static List<ProcessDefinition> getProcessDefinitionByIds(Set<String> deploymentIds) throws Exception{
		List<ProcessDefinition> pdList = getProcessEngine()
				.getRepositoryService()
				.createProcessDefinitionQuery()////创建流程实例查询，查询正在执行的流程实例
				.deploymentIds(deploymentIds)   //按流程实例id查询
				.orderByProcessDefinitionVersion()
				.asc()
				.list();
		return pdList;
	}
	/**
	 * 删除流程 
	 * @param definitionId definitionId
	 * @param groupId groupId
	 * @throws Exception
	 */
	public static void deleteDeploymentByGroupId(String definitionId,String groupId) throws Exception{
		getProcessEngine()
		.getRepositoryService()
		.deleteCandidateStarterGroup(definitionId, groupId);
	}
	/**
	 * 删除流程 
	 * @param definitionId definitionId
	 * @param userId userId
	 * @throws Exception
	 */
	public static void deleteDeploymentByUserId(String definitionId,String userId) throws Exception{
		getProcessEngine()
		.getRepositoryService()
		.deleteCandidateStarterUser(definitionId, userId);
	}
	
	/**
	 * 删除流程 
	 * @param definitionId definitionId
	 * @param cascade cascade
	 * @throws Exception
	 */
	public static void deleteDeployment(String deploymentId,boolean cascade) throws Exception{
		getProcessEngine()
		.getRepositoryService()
		.deleteDeployment(deploymentId, cascade);
	}
	/**
	 * 删除流程（使用流程定义的key）
	 * @param cascade
	 * @throws Exception
	 */
	public static void deleteDeploymentByProcessKey(String processName,boolean cascade) throws Exception{
		List<ProcessDefinition> pdList = getProcessDefinitionForList(processName);
		for(ProcessDefinition definition : pdList){
			getProcessEngine()
			.getRepositoryService()
			.deleteDeployment(definition.getDeploymentId(), cascade);
		}
	}
	
	/**
	 * 
	 * @param deploymentId deploymentId
	 * @param imageUrl 图片的输出路径
	 * @throws Exception
	 */
	public static void viewImage(String deploymentId,String imageUrl) throws Exception{
		RepositoryService repositoryService = getProcessEngine().getRepositoryService();
		List<String> images = repositoryService.getDeploymentResourceNames(deploymentId);
		String imageName = null;
		for(String name:images){
			if(name.indexOf(".png")>=0){
				imageName = name;
			}
		}
		if(imageName!=null&&!imageName.equals("")){
			File imageFile = new File(imageUrl);
			InputStream is = repositoryService.getResourceAsStream(deploymentId, imageName);
			FileUtils.copyInputStreamToFile(is, imageFile);
		}
	}
	/**
	 * 查看个人任务
	 * @param assignee
	 * @return
	 * @throws Exception
	 */
	public static List<Task> TastFindByUser(String assignee) throws Exception{
		List<Task> taskList = getProcessEngine()  
				.getTaskService()
				.createTaskQuery()
				.taskAssignee(assignee)
				.orderByTaskCreateTime()
				.asc()
				.list();
		return taskList;
	}
	
	/**
	 * 办理业务
	 * @param taskId  任务Id
	 * @throws Exception
	 */
	public static void complete(String taskId) throws Exception{
		getProcessEngine().getTaskService().complete(taskId);
	}
	/**
	 *在流程执行的过程中，
	 *创建的流程实例ID在整个过程中都不会变，
	 *当流程结束后，
	 *流程实例将会在正在执行的执行对象表中（act_ru_execution）被删除  
	 *说明：  
	 *1) 因为是查询流程实例，所以先获取runtimeService 
	 *2) 创建流程实例查询对象，设置实例ID过滤参数  
	 *3) 由于一个流程实例ID只对应一个实例，使用singleResult执行查询返回一个唯一的结果， 如果结果数量大于1，则抛出异常 
	 *4) 判断指定ID的实例是否存在，如果结果为空，则代表流程结束，实例在正在执行的执行对象表中已被删除，转换成历史数据。
	 * @param instanceId
	 * @throws Exception
	 */
	public static Map<String,Object> findStateByInstanceId(String instanceId) throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		ProcessInstance instance = getProcessEngine()
				.getRuntimeService()
				.createProcessInstanceQuery()
				.processInstanceId(instanceId)
				.singleResult();
		map.put("activityId", instance.getActivityId());
		map.put("businessKey", instance.getBusinessKey());
		map.put("deploymentId", instance.getDeploymentId());
		map.put("name", instance.getName());
		map.put("description", instance.getDescription());
		map.put("id", instance.getId());
		map.put("instanceId", instanceId);
		return map;
	}
	
	/**
	 * 查询历史审批记录
	 * @param processInstanceId
	 * @return
	 * @throws Exception
	 */
	public static List<HistoricProcessInstance> getHistoryTask(String processInstanceId) throws Exception{
		List<HistoricProcessInstance> listHistory= getProcessEngine()
				.getHistoryService()
				.createHistoricProcessInstanceQuery()
				.processInstanceId(processInstanceId)
				.list();
		return listHistory;
	}
	
	/**
	 * 查询指定流程的任务流转路径 （流程历史 任务 流转 路经）
	 * @param processInstanceId  流程id
	 * @param firstResult 开始记录
	 * @param maxResults 结束记录
	 * @return
	 * @throws Exception
	 */
	public static List<HistoricProcessInstance> getHistoryInstance(String processInstanceId,int firstResult, int maxResults) throws Exception{
		List<HistoricProcessInstance> listHistory= getProcessEngine()
				.getHistoryService()
				.createHistoricProcessInstanceQuery()
				.processInstanceId(processInstanceId)
				.listPage(firstResult, maxResults);
		return listHistory;
	}
	
	/**
	 * 查询指定用户参与的流程信息 （流程历史  用户参与 ）
	 * @param processInstanceId  流程id
	 * @param firstResult 开始记录
	 * @param maxResults 结束记录
	 * @return
	 * @throws Exception
	 */
	public static List<HistoricProcessInstance> getHistoryByName(String name,int firstResult, int maxResults) throws Exception{
		List<HistoricProcessInstance> listHistory= getProcessEngine()
				.getHistoryService()
				.createHistoricProcessInstanceQuery()
				.involvedUser(name)
				.listPage(firstResult, maxResults);
		return listHistory;
	}
	/**
	 * 查询指定流程信息
	 * @param assignee  流程id
	 * @param firstResult 开始记录
	 * @param maxResults 结束记录
	 * @return
	 * @throws Exception
	 */
	public static List<HistoricTaskInstance> getHistoryByTask(String assignee,int firstResult, int maxResults) throws Exception{
		List<HistoricTaskInstance> listHistory= getProcessEngine()
				.getHistoryService()
				.createHistoricTaskInstanceQuery()
				.taskAssignee(assignee)
				.listPage(firstResult, maxResults);
		return listHistory;
	}
	
	/**
	 * 查询指定流程信息
	 * @param processInstanceId
	 * @return
	 * @throws Exception
	 */
	public static List<HistoricVariableInstance> getHistoryVariablesList(String processInstanceId,int firstResult, int maxResults) throws Exception{  
	    List<HistoricVariableInstance> listHistory = getProcessEngine().getHistoryService()  
	            .createHistoricVariableInstanceQuery()  
	            .processInstanceId(processInstanceId)  
	            .listPage(firstResult, maxResults);  
	    return listHistory;
	}
	
	/**
	 * 设置参数
	 * @param variables 参数
	 * @throws Exception
	 */
	public static void setVariables(String taskId , Map<String,Object>variables) throws Exception{
		getProcessEngine().getTaskService().setVariables(taskId, variables);
	}
	
	/**
	 * 设置参数
	 * @param variables 参数
	 * @throws Exception
	 */
	public static Map<String,Object> getVariables(String taskId) throws Exception{
		Map<String,Object> variables = getProcessEngine().getTaskService().getVariables(taskId);
		return variables;
	}
}
