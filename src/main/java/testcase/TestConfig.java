package testcase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;

public class TestConfig {
    
// Return String[] of defined tests
@Value("${test.cases}")
private String[] testCasesList;

public String[] getTestCasesList() {
    return testCasesList;
}

public List<String> TestCaseListRandom() {
    
    // Convert String[] into ArrayList
    int cs_num = testCasesList.length;
    List<String> caseListOrdered = new ArrayList<String>();
    
    for (int i=0; i<cs_num; i++) {
	caseListOrdered.add(testCasesList[i]);
    }
    Collections.shuffle(caseListOrdered);
    return caseListOrdered;
}

    
}
