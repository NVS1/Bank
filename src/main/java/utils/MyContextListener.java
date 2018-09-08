package utils;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyContextListener implements ServletContextListener {
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("JPA");
        servletContextEvent.getServletContext().setAttribute("emf", factory);
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        EntityManagerFactory emf = (EntityManagerFactory) servletContextEvent
                .getServletContext()
                .getAttribute("emf");
        emf.close();
    }
}
