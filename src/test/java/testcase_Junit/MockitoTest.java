package testcase_Junit;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import org.junit.Test;
import testcase.*;


public class MockitoTest {

    @Test
    public void test() {
	TestCase testCase = mock(TestCase.class);
	assertTrue(true);
    }

}
