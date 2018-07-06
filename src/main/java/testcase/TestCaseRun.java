package testcase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

@Component
public class TestCaseRun {

    
    @SuppressWarnings("unused")
    private static Logger logger = LoggerFactory.getLogger(TestCaseRun.class);
        

    public static void main(String args[]) throws Exception {

	AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
	ThreadPoolTaskExecutor executor = (ThreadPoolTaskExecutor) context.getBean(ThreadPoolTaskExecutor.class);
	
	int conc_users = executor.getCorePoolSize();
	
	Logger logger = LoggerFactory.getLogger(TestCase.class);
	logger.info("Start files reading");
	
	
	TestCase testCase = (TestCase) context.getBean(TestCase.class, "source_file1.txt");//new TestCase("source_file1.txt");
	TestCase testCase2 = (TestCase) context.getBean(TestCase.class, "source_file2.txt");//new TestCase("source_file2.txt");

	logger.info("Files loaded, connecting");

	for (int i=0;i<conc_users; i++) {
		//executor.execute(testCase.execute());
	logger.info("----------------------------------------------");
	logger.info("Thread of user " + (int) (i+1) + " starting");
	testCase.execute();
	testCase2.execute();
	}
	Thread.sleep(1000);
	System.out.println("After threads");
	executor.shutdown();
	
		
	//System.out.println("Number of Users: " + 

	
	//logger.info("Read from file " + ${conc_users})
	//testCase.execute();
	//testCase2.execute();
    }

}