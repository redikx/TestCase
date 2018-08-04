package testcase;

import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import datamodel.RunsDAO_interface;

@Component
public class TestCaseRun {

	private static final Logger logger = LoggerFactory.getLogger(TestCaseRun.class);

	public static void main(String args[]) {
		logger.info("Logging Start in DB");
		
		int counter=1;
		while (counter<10) {
			counter++;

			AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
			ThreadPoolTaskExecutor executor = (ThreadPoolTaskExecutor) context.getBean(ThreadPoolTaskExecutor.class);
			executor.setWaitForTasksToCompleteOnShutdown(true);	
		RunsDAO_interface runsTbl = (RunsDAO_interface) context.getBean("runsDAO");
		TestConfig tc = context.getBean(TestConfig.class);
			
		
			int conc_users = executor.getCorePoolSize();
			int run_id = runsTbl.insertRuns(conc_users);
			

			for (int i = 1; i <= conc_users; i++) {
				logger.info("Thread of user " + (int) (i) + " starting");
				List<String> RandomList = tc.TestCaseListRandom();
				for (int j = 0; j < RandomList.size(); j++) {
					TestCase testCase = (TestCase) context.getBean(TestCase.class, RandomList.get(j));

					try {
						Thread.sleep(1000);
						testCase.readRunId(run_id);
						// System.out.println("EXECUTING THREAD " + i + " FILE " + RandomList.get(j));
						executor.execute(testCase);
					} catch (Exception e) {
						logger.error(e.getMessage(), e);
					} finally {
						runsTbl.updateETime(run_id);
					}
				}
			}
			try {
				Thread.sleep(1000);
				executor.shutdown();
			} catch (InterruptedException e) {
				logger.info("Interrputed exception");
			}

//context.close();
		}
	}

}
