package testcase;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
//@Scope("prototype")
public class ThreadRun implements Runnable {

    public void run() {
	System.out.println("RUN THREAD");
    }

    
}
