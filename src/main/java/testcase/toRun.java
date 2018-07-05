package testcase;

import org.springframework.stereotype.Component;

@Component
public class toRun  implements Runnable{

    public void run() {
System.out.println("THREAD EXECUTED");
    }

}
