package servlet;

import dao.JpaDAO;
import model.Account;
import model.Client;
import service.AccountService;
import service.ClientService;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AccountsServlet", urlPatterns = "/accounts")
public class AccountsServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String phone = request.getParameter("phone");
        EntityManagerFactory emf = (EntityManagerFactory) getServletContext().getAttribute("emf");
        EntityManager em = emf.createEntityManager();
        JpaDAO clientDao = new ClientService(em);
        Client client = (Client) clientDao.get(phone);
        try {
            List<Account> accounts = client.getAccounts();
            request.setAttribute("name", client.getName());
            request.setAttribute("accounts", accounts);
            request.getRequestDispatcher("accounts.jsp").forward(request,response);
        } finally {
            em.close();
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String number = request.getParameter("number");
        String money = request.getParameter("money");
        if (number.isEmpty() || money.isEmpty()){
            response.getWriter().print("Error");
        }
        Double parseMoney = Double.parseDouble(money)*1000;
        EntityManagerFactory emf = (EntityManagerFactory) getServletContext().getAttribute("emf");
        EntityManager em = emf.createEntityManager();
        JpaDAO accountDao = new AccountService(em);
        em.getTransaction().begin();
        try{
            Account account = (Account) accountDao.get(number);
            account.debit(parseMoney.longValue());
            em.getTransaction().commit();
            response.sendRedirect("/");
        } catch (NoResultException ex){
            response.getWriter().print("Account not found");
        } catch (Exception ex){
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
}
