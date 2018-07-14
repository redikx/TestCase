package testcase;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

public class TestConfig {
    
// Return String[] of defined tests
@Value("${test.cases}")
private String[] testCasesList;

public String[] getTestCasesList() {
    return testCasesList;
}

    
}
