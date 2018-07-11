package testcase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

@Component
public class TestCaseRun {

    private static final Logger logger = LoggerFactory.getLogger(TestCaseRun.class);

    public static void main(String args[]) {

	AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);

	ThreadPoolTaskExecutor executor = (ThreadPoolTaskExecutor) context.getBean(ThreadPoolTaskExecutor.class);
	executor.setWaitForTasksToCompleteOnShutdown(true);

	int conc_users = executor.getCorePoolSize();

	logger.info("Start files reading");

	TestCase testCase = (TestCase) context.getBean(TestCase.class, "source_file1.txt");// new
											   // TestCase("source_file1.txt");
	TestCase testCase2 = (TestCase) context.getBean(TestCase.class, "source_file2.txt");// new
											    // TestCase("source_file2.txt");

	logger.info("Files loaded, connecting");
	
	System.out.println("USERS : " + conc_users);
	for (int i = 1; i <= conc_users; i++) {
	    logger.info("Thread of user " + (int) (i) + " starting");

	    // it needs to be rewritten to call execute once
	    try {
		    executor.execute(testCase);
		    executor.execute(testCase2);
	    } catch (Exception e) {
		logger.error(e.getMessage(), e);
	    }
	}
	
	try {
	    Thread.sleep(1000);
	    executor.shutdown();
	} catch (InterruptedException e) {
	    logger.info("Interrputed exception");
	}
	
    }

}
