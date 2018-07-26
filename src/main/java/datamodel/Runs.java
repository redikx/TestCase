package datamodel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="Runs")
public class Runs {

@Id
@Column(name = "run_id")
@GeneratedValue( strategy = GenerationType.IDENTITY)
private int run_id;

@Column(name = "users")
private int users;

@Column(name = "start_time", columnDefinition = "DATETIME")
@Temporal(TemporalType.TIMESTAMP)
private Date start_time;

@Column(name = "end_time", columnDefinition = "DATETIME")
@Temporal(TemporalType.TIMESTAMP)
private Date end_time;

@OneToMany(mappedBy="runs",
			cascade= {CascadeType.PERSIST, CascadeType.MERGE,
					  CascadeType.DETACH, CascadeType.REFRESH})
private List<Run_Cases> run_cases;

public List<Run_Cases> getCases() {
	return run_cases;
}

public void setCases(List<Run_Cases> cases) {
	this.run_cases = cases;
}

// add convenience methods for bi-directional relationship

public void add(Run_Cases tempRun_Cases) {
	
	if (run_cases == null) {
		run_cases = new ArrayList<Run_Cases>();
	}
	run_cases.add(tempRun_Cases);
	
} 

public int getRun_id() {
	return run_id;
}

public void setRun_id(int run_id) {
	this.run_id = run_id;
}

public int getUsers() {
	return users;
}

public void setUsers(int users) {
	this.users = users;
}

public Date getStart_time() {
	return start_time;
}

public void setStart_time(Date start_time) {
	this.start_time = start_time;
}

public Date getEnd_time() {
	return end_time;
}

public void setEnd_time(Date end_time) {
	this.end_time = end_time;
}

@Override
public String toString() {
	return "Runs [run_id=" + run_id + ", users=" + users + ", start_time=" + start_time + ", end_time=" + end_time
			+ "]";
}

	
}
