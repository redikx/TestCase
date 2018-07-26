package datamodel;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class RunsDAO implements RunsDAO_interface {

	public RunsDAO() {    
	}
	    	
	@Autowired
	private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

	public void save(RunsDAO p) {
		// TODO Auto-generated method stub
		
	}

	public int insertRuns(int users) {
		Session session = this.sessionFactory.openSession();
		//DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SS");
		Date Btime = new Date();
		session.beginTransaction();
		Runs runs_tbl = new Runs();
		runs_tbl.setUsers(users);
		runs_tbl.setStart_time(Btime);
		runs_tbl.setEnd_time(null);
		session.getTransaction().commit();
		session.save(runs_tbl);		
		return runs_tbl.getRun_id();
	}

	public void updateETime(int run_id) {
		Session session = this.sessionFactory.openSession();
		//DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SS");
		Date Etime = new Date();
		session.beginTransaction();
		Runs runs_tbls = session.load(Runs.class, new Integer(run_id));
		runs_tbls.setEnd_time(Etime);
		session.getTransaction().commit();
		session.save(runs_tbls);		
		
	}

	public void insertCases(int RunId, String casename) {
		// run_Id from input
		Session session = this.sessionFactory.openSession();
		Date Etime = new Date();
		session.beginTransaction();
		Run_Cases rc = new Run_Cases();
		Runs run = session.load(Runs.class, new Integer(RunId));
		System.out.println("Call run(add) for : " + rc.toString());
		rc.setCase_Start_time(Etime);
		rc.setCase_Name(casename);
		run.add(rc);
		session.getTransaction().commit();
		session.save(rc);
	}



}
