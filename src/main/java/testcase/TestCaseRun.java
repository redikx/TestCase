package testcase;

import java.io.IOException;
import java.net.SocketException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestCaseRun {

    private static Logger logger = LoggerFactory.getLogger(TestCaseRun.class);

    public static void main(String args[]) throws Exception {

	AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);

	Logger logger = LoggerFactory.getLogger(TestCase.class);
	logger.info("Start files reading");

	
	TestCase testCase = (TestCase) context.getBean(TestCase.class, "source_file1.txt");//new TestCase("source_file1.txt");
	TestCase testCase2 = (TestCase) context.getBean(TestCase.class, "source_file2.txt");//new TestCase("source_file2.txt");

	logger.info("Files loaded, connecting");
	testCase.execute();
	testCase2.execute();
    }

}