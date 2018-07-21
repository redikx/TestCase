package testcase;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import datamodel.Test_Table;
import datamodel.Test_TableDAO_interface;



@Component
public class TestCaseRun {
    
   private static final Logger logger = LoggerFactory.getLogger(TestCaseRun.class);

    public static void main(String args[]) {

	
	
	
	AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
	
	logger.info("##################Test Db Connection");
	Test_TableDAO_interface test_TableDAO = (Test_TableDAO_interface) context.getBean("test_TableDAO");
	Test_Table tt = new Test_Table();
	test_TableDAO.save(tt);
	logger.info("###################End Db Connection");
	
	System.exit(0);
	
	
	TestConfig tc = context.getBean(TestConfig.class);

	ThreadPoolTaskExecutor executor = (ThreadPoolTaskExecutor) context.getBean(ThreadPoolTaskExecutor.class);
	executor.setWaitForTasksToCompleteOnShutdown(true);
	int conc_users = executor.getCorePoolSize();
	System.out.println("USERS : " + conc_users);
	
	for (int i = 1; i <= conc_users; i++) {
	    logger.info("Thread of user " + (int) (i) + " starting");
	    List<String> RandomList = tc.TestCaseListRandom();
	    for (int j = 0; j < RandomList.size(); j++) {
		TestCase testCase = (TestCase) context.getBean(TestCase.class, RandomList.get(j));
		try {
		   // executor.execute(testCase);
		} catch (Exception e) {
		    logger.error(e.getMessage(), e);
		}
	    }
	}
	try {
	    Thread.sleep(1000);
	    executor.shutdown();
	} catch (InterruptedException e) {
	    logger.info("Interrputed exception");
	}
context.close();

    }

}
