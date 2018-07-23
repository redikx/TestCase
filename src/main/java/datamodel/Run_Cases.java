package datamodel;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name="run_Cases")
public class Run_Cases {

@Id
@Column(name="run_Case_id")
@GeneratedValue( strategy = GenerationType.IDENTITY)
private int run_Case_id;

@Column(name="case_Name")
private String case_Name;

@Column(name = "case_Start_time", columnDefinition = "DATETIME")
@Temporal(TemporalType.TIMESTAMP)
private Date case_Start_time;

@Column(name = "case_End_time", columnDefinition = "DATETIME")
@Temporal(TemporalType.TIMESTAMP)
private Date case_End_time;

@Column(name = "result")
private String result;

@ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
		 CascadeType.DETACH, CascadeType.REFRESH})
@JoinColumn(name="run_id")
private Runs runs; 

@Column()

public int getRun_Case_id() {
	return run_Case_id;
}

public void setRun_Case_id(int run_Case_id) {
	this.run_Case_id = run_Case_id;
}

public String getCase_Name() {
	return case_Name;
}

public void setCase_Name(String case_Name) {
	this.case_Name = case_Name;
}

public Date getCase_Start_time() {
	return case_Start_time;
}

public void setCase_Start_time(Date case_Start_time) {
	this.case_Start_time = case_Start_time;
}

public Date getCase_End_time() {
	return case_End_time;
}

public void setCase_End_time(Date case_End_time) {
	this.case_End_time = case_End_time;
}

public String getResult() {
	return result;
}

public void setResult(String result) {
	this.result = result;
}

@Override
public String toString() {
	return "Run_Cases [run_Case_id=" + run_Case_id + ", case_Name=" + case_Name + ", case_Start_time=" + case_Start_time
			+ ", case_End_time=" + case_End_time + ", result=" + result + "]";
}

}