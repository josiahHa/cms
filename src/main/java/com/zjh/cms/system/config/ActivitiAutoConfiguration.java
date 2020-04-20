package com.zjh.cms.system.config;

import java.io.IOException;

import javax.sql.DataSource;

//import org.activiti.engine.FormService;
//import org.activiti.engine.HistoryService;
//import org.activiti.engine.IdentityService;
//import org.activiti.engine.RepositoryService;
//import org.activiti.engine.RuntimeService;
//import org.activiti.engine.TaskService;
//import org.activiti.spring.ProcessEngineFactoryBean;
//import org.activiti.spring.SpringProcessEngineConfiguration;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.Resource;
//import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
//import org.springframework.transaction.PlatformTransactionManager;

/**
 * activiti自动配置类
 * 
 * @author LJH
 *
 */
//@Configuration
public class ActivitiAutoConfiguration {

	
//	@Autowired
//	private DataSource dataSource;
//
//	@Autowired
//	private PlatformTransactionManager platformTransactionManager;
//
//
//	@Bean
//	public SpringProcessEngineConfiguration springProcessEngineConfiguration(){
//		SpringProcessEngineConfiguration spec = new SpringProcessEngineConfiguration();
//        spec.setDataSource(dataSource);
//        spec.setTransactionManager(platformTransactionManager);
//		spec.setDatabaseSchemaUpdate("true");
//		Resource[] resources = null;
//        // 启动自动部署流程
//		try {
//			resources = new PathMatchingResourcePatternResolver().getResources("classpath*:bpmn/*.bpmn");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		spec.setDeploymentResources(resources);
//		return spec;
//	}
//
//	@Bean
//	public ProcessEngineFactoryBean processEngine(SpringProcessEngineConfiguration springProcessEngineConfiguration){
//		ProcessEngineFactoryBean processEngineFactoryBean = new ProcessEngineFactoryBean();
//		processEngineFactoryBean.setProcessEngineConfiguration(springProcessEngineConfiguration);
//		return processEngineFactoryBean;
//	}
//
//
//	@Bean
//	public RepositoryService repositoryService(ProcessEngineFactoryBean processEngine) throws Exception{
//		return processEngine.getObject().getRepositoryService();
//	}
//	@Bean
//	public RuntimeService runtimeService(ProcessEngineFactoryBean processEngine) throws Exception{
//		return processEngine.getObject().getRuntimeService();
//	}
//	@Bean
//	public TaskService taskService(ProcessEngineFactoryBean processEngine) throws Exception{
//		return processEngine.getObject().getTaskService();
//	}
//	@Bean
//	public HistoryService historyService(ProcessEngineFactoryBean processEngine) throws Exception{
//		return processEngine.getObject().getHistoryService();
//	}
//	@Bean
//	public IdentityService identityService(ProcessEngineFactoryBean processEngine) throws Exception{
//		return processEngine.getObject().getIdentityService();
//	}
//
//	@Bean
//	public FormService formService(ProcessEngineFactoryBean processEngine) throws Exception{
//		return processEngine.getObject().getFormService();
//	}
}
