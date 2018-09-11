package servlet;

import dao.JpaDAO;
import model.Account;
import model.Client;
import model.Currency;
import model.Rate;
import service.ClientService;
import service.RateService;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "TotalMoneyServlet", urlPatterns = "/money")
public class TotalMoneyServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String phone = request.getParameter("phone");
        if (phone.isEmpty()){
            response.getWriter().print("Error");
        }
        EntityManagerFactory emf = (EntityManagerFactory) getServletContext().getAttribute("emf");
        EntityManager em = emf.createEntityManager();
        JpaDAO clientDao = new ClientService(em);
        JpaDAO rateDao = new RateService(em);
        Client client = ((ClientService) clientDao).get(phone);
        List<Account> accounts = client.getAccounts();
        Long totalMoney = Long.valueOf(0);
        for (Account account : accounts) {
            if (account.getCurrency().equals(Currency.UAH)){
                totalMoney+=account.getMoney();
            }else {
                totalMoney+=account.getMoneyInUAH(((RateService) rateDao).get(account.getCurrency().toString()+Currency.UAH));
            }
        }
        response.getWriter().print("Total money = "+(totalMoney/1000)+" UAH");
    }
}
