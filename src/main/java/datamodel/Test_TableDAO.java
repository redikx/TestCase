package datamodel;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Component
public class Test_TableDAO implements Test_TableDAO_interface {
    
    	public Test_TableDAO() {    
    	}
    	    	
    	@Autowired
    	private SessionFactory sessionFactory;

        public void setSessionFactory(SessionFactory sessionFactory) {
            this.sessionFactory = sessionFactory;
        }
        
        
        @Transactional(propagation=Propagation.REQUIRED)
    	public void save(Test_Table p) {
    		Session session = this.sessionFactory.getCurrentSession();
    		session.persist(p);
    	}

    	@SuppressWarnings("unchecked")
    	public List<Test_Table> list() {
    		Session session = this.sessionFactory.openSession();
    		List<Test_Table> Test_TableList = session.createQuery("from Test_Table").getResultList();
    		session.close();
    		return Test_TableList;
    	}


    }