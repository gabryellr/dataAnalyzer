package com.gabryelrock.core.temaFinal;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args)  {

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config.class);
        ListenDirectory listenDirectory = (ListenDirectory) applicationContext.getBean("listenDirectory");

        listenDirectory.run();
    }
}
