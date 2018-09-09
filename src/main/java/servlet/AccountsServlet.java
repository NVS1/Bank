package servlet;

import dao.JpaDAO;
import model.Account;
import model.Client;
import service.AccountService;
import service.ClientService;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AccountsServlet", urlPatterns = "/accounts")
public class AccountsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
}
