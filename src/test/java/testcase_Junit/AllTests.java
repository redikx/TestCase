package testcase_Junit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ testCase_FilesCheck.class,ServerTest.class,MockitoTest.class })
public class AllTests {

}
