package com.comp.activiti;

import java.util.List;
import java.util.Map;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.repository.ProcessDefinition;
import org.apache.log4j.Logger;
import org.junit.Test;

import com.base.util.activiti.ActivitiUtils;


/**
 *项目名：
 *创建时间：2017-4-1
 *创建人：Aobo
 *类名：ProcessEngineTest
 *所属包名：com.comp.activiti
 *项目名称：comp
 *类功能描述：
 */
public class ProcessEngineTest {
	private final Logger logger=Logger.getLogger(ProcessEngineTest.class);
	@Test
	public void testProcessEngine() throws Exception{
		ProcessEngine processEngine = ActivitiUtils.getProcessEngine();
		System.out.println(processEngine.getName());
	}
	@Test
	public void testDeploy() throws Exception{
//		String url = this.getClass().getResource("testProcess.zip").getPath();
//		ActivitiUtils.deploy(url);
//		ActivitiUtils.startProcess("myProcess");
//		List<ProcessDefinition> pdList = ActivitiUtils.getProcessDefinitionForList("myProcess");
//		for(ProcessDefinition definition : pdList){
//			System.out.println(definition.getDeploymentId());
//			System.out.println(definition.getDiagramResourceName());
//			System.out.println(definition.getId());
//			System.out.println(definition.getKey());
//			System.out.println(definition.getName());
//			System.out.println(definition.getResourceName());
//			System.out.println(definition.getVersion());
//		}
//		Map<String,Object> map = ActivitiUtils.findStateByInstanceId("2501");
//		for(Map.Entry<String,Object> entry : map.entrySet()){
//			System.out.println("Key===="+entry.getKey()+"   value===="+entry.getValue());
//		}
		
		List<HistoricProcessInstance> hisList = ActivitiUtils.getHistoryTask("2501");
		for(HistoricProcessInstance his : hisList){
			System.out.println(his.getStartTime());
		}
		
	}
}

