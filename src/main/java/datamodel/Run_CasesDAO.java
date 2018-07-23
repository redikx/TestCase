package datamodel;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

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
			return rc.getRun_Case_id();
			/*runs_tbl.setUsers(users);
			runs_tbl.setStart_time(Btime);
			runs_tbl.setEnd_time(null);
			session.getTransaction().commit();
			session.save(runs_tbl);		
			return runs_tbl.getRun_id();*/
			//return 0;
		}

	


	}


