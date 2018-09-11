package servlet;

import dao.JpaDAO;
import model.Account;
import model.Client;
import model.Rate;
import model.Transaction;
import service.AccountService;
import service.ClientService;
import service.RateService;
import service.TransactionService;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@WebServlet(name = "TransactionsServlet", urlPatterns = "/transactions")
public class TransactionsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String phone = request.getParameter("phone");
        String accountFrom = request.getParameter("from");
        String accountTo = request.getParameter("to");
        String money = request.getParameter("money");
        if  (phone.isEmpty() || accountFrom.isEmpty() || accountTo.isEmpty() || money.isEmpty()){
            response.getWriter().print("Error");
        }
        Double parseMoney = Double.parseDouble(money)*100;
        EntityManagerFactory emf = (EntityManagerFactory) getServletContext().getAttribute("emf");
        EntityManager em = emf.createEntityManager();
        JpaDAO clientDao = new ClientService(em);
        JpaDAO accountDao = new AccountService(em);
        JpaDAO transactionsDao = new TransactionService(em);
        JpaDAO rateDao = new RateService(em);
        Client client = ((ClientService) clientDao).get(phone);
        Account from = ((AccountService) accountDao).get(accountFrom);
        if (!from.getOwner().equals(client)){
            response.getWriter().print("This client have not such an account");
        } else {
            Account to = ((AccountService) accountDao).get(accountTo);
            if (to == null){
                response.getWriter().print("account "+ to.getNumber()+" don't exist");
            }
            Rate rate = null;
            if (!from.getCurrency().equals(to.getCurrency())){
                rate = ((RateService) rateDao).get(from.getCurrency().toString()+to.getCurrency().toString());
            }
            Transaction transaction = new Transaction(client, from, to, parseMoney.longValue());
            transaction.setRate(rate);
            transaction.setDate(new Date());
            transactionsDao.add(transaction);
            boolean success = transaction.doTransaction(em);
            if (!success){
                response.getWriter().print("Failed");
            } else {
                response.sendRedirect("/");
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String phone = request.getParameter("phone");
        EntityManagerFactory emf = (EntityManagerFactory) getServletContext().getAttribute("emf");
        EntityManager em = emf.createEntityManager();
        JpaDAO clientDao = new ClientService(em);
        Client client = ((ClientService) clientDao).get(phone);
        try{
            List<Transaction> transactions = client.getTransactions();
            request.setAttribute("name", client.getName());
            request.setAttribute("transactions", transactions);
            request.getRequestDispatcher("transactions.jsp").forward(request,response);
        } finally {
            em.close();
        }
    }
}
