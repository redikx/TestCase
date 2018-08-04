package datamodel;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.sun.media.jfxmedia.logging.Logger;

//@Transactional
public class Run_CasesDAO implements Run_CasesDAO_interface {

	public Run_CasesDAO() {
	}
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public int insertRuns(String Case_name) {
			Session session = this.sessionFactory.openSession();
			Date Btime = new Date();
			session.beginTransaction();
			Run_Cases rc = new Run_Cases();
			rc.setCase_Name(Case_name);
			rc.setCase_Start_time(Btime);
			rc.setCase_End_time(null);
			rc.setResult(null);
			session.getTransaction().commit();
			session.save(rc);
			session.close();
			return rc.getRun_Case_id();

		}

	public void updateETime(int Run_Case_Id, String Result) {
		Session session = this.sessionFactory.openSession();
		Date Etime = new Date();
		session.beginTransaction();
		Run_Cases rc = session.load(Run_Cases.class,new Integer(Run_Case_Id));
		rc.setCase_End_time(Etime);
		rc.setResult(Result);
		session.getTransaction().commit();
		session.save(rc);	
		session.close();
	}

/*		Session session = this.sessionFactory.openSession();
		//DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SS");
		Date Etime = new Date();
		session.beginTransaction();
		Runs runs_tbls = session.load(Runs.class, new Integer(run_id));
		runs_tbls.setEnd_time(Etime);
		session.getTransaction().commit();
		session.save(runs_tbls);		*/	


	}


