package datamodel;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="Runs")
public class Runs {

@Id
@Column(name = "Run_id")
@GeneratedValue( strategy = GenerationType.IDENTITY)
private int Run_id;

@Column(name = "Users")
private int Users;

@Column(name = "Start_time", columnDefinition = "DATETIME")
@Temporal(TemporalType.TIMESTAMP)
private Date Start_time;

@Column(name = "End_time", columnDefinition = "DATETIME")
@Temporal(TemporalType.TIMESTAMP)
private Date End_time;

public int getRun_id() {
	return Run_id;
}

public void setRun_id(int run_id) {
	Run_id = run_id;
}

public int getUsers() {
	return Users;
}

public void setUsers(int users) {
	Users = users;
}

public Date getStart_time() {
	return Start_time;
}

public void setStart_time(Date start_time) {
	Start_time = start_time;
}

public Date getEnd_time() {
	return End_time;
}

public void setEnd_time(Date end_time) {
	End_time = end_time;
}

@Override
public String toString() {
	return "Runs [Run_id=" + Run_id + ", Users=" + Users + ", Start_time=" + Start_time + ", End_time=" + End_time
			+ "]";
}

	
}
