package utils;

import dao.RateDAO;
import service.RateService;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyContextListener implements ServletContextListener {
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("JPATest");
        servletContextEvent.getServletContext().setAttribute("emf", factory);
        addExchangeRate(factory.createEntityManager());
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        EntityManagerFactory emf = (EntityManagerFactory) servletContextEvent
                .getServletContext()
                .getAttribute("emf");
        emf.close();
    }

    public void addExchangeRate(EntityManager em) {
        RateDAO rateDAO = new RateService(em);
        try {
            rateDAO.init();
        } finally {
            em.close();
        }
    }
}
