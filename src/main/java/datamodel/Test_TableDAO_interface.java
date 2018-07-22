package datamodel;

import java.util.List;

public interface Test_TableDAO_interface {
    
    void save(Test_Table p);	
    List<Test_Table> list();
    void deleteEmptyTest_Table();
    int countRowsTest_Table();
}
