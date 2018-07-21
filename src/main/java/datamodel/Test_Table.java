package datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Id;

@Entity
@Table(name="test_table")
public class Test_Table {
    
@Id
@Column(name="Id")
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int Id;

@Column(name="db_name")
private String db_name;

public Test_Table() {
    super();
}

public int getId() {
    return Id;
}

public void setId(int id) {
    Id = id;
}

public String getDb_name() {
    return db_name;
}

public void setDb_name(String db_name) {
    this.db_name = db_name;
}

@Override
public String toString() {
    return "Test_Table [Id=" + Id + ", db_name=" + db_name + "]";
}






}
