package datamodel;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="Run_Cases")
public class Run_Cases {

@Id
@Column(name="Run_Case_id")
@GeneratedValue( strategy = GenerationType.IDENTITY)
private int Run_Case_id;

@JoinColumn(name="Run_id")
@OneToOne(cascade = CascadeType.ALL)
private Runs runs;

public Runs getRuns() {
	return runs;
}

public void setRuns(Runs runs) {
	this.runs = runs;
}

@Column(name="Case_Name")
private String Case_Name;

@Column(name = "Case_Start_time", columnDefinition = "DATETIME")
@Temporal(TemporalType.TIMESTAMP)
private Date Case_Start_time;

@Column(name = "Case_End_time", columnDefinition = "DATETIME")
@Temporal(TemporalType.TIMESTAMP)
private Date Case_End_time;

public int getRun_Case_id() {
	return Run_Case_id;
}

public void setRun_Case_id(int run_Case_id) {
	Run_Case_id = run_Case_id;
}

public String getCase_Name() {
	return Case_Name;
}

public void setCase_Name(String case_Name) {
	Case_Name = case_Name;
}

public Date getCase_Start_time() {
	return Case_Start_time;
}

public void setCase_Start_time(Date case_Start_time) {
	Case_Start_time = case_Start_time;
}

public Date getCase_End_time() {
	return Case_End_time;
}

public void setCase_End_time(Date case_End_time) {
	Case_End_time = case_End_time;
}

@Override
public String toString() {
	return "Run_Cases [Run_Case_id=" + Run_Case_id + ", runs=" + runs + ", Case_Name=" + Case_Name
			+ ", Case_Start_time=" + Case_Start_time + ", Case_End_time=" + Case_End_time + "]";
}



}
