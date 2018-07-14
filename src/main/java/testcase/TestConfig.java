package testcase;

import java.util.ArrayList;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Value;

public class TestConfig {
    
// Return String[] of defined tests
@Value("${test.cases}")
private String[] testCasesList;

public String[] getTestCasesList() {
    return testCasesList;
}

public ArrayList<String> TestCaseListRandom() {
    
    // Convert String[] into ArrayList
    int cs_num = testCasesList.length;
    ArrayList<String> CaseListOrdered = new ArrayList<String>();
    for (int i=0; i<cs_num; i++) {
	CaseListOrdered.add(testCasesList[i]);
    }
    Collections.shuffle(CaseListOrdered);
    return CaseListOrdered;
}

    
}
