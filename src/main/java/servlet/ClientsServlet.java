package servlet;

import model.Account;
import model.Currency;
import service.ClientService;
import dao.JpaDAO;
import model.Client;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ClientsServlet", urlPatterns = "/clients")
public class ClientsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        EntityManagerFactory emf = (EntityManagerFactory) getServletContext().getAttribute("emf");
        EntityManager em = emf.createEntityManager();
        JpaDAO clientDao = new ClientService(em);
        Client client = new Client(name, phone);
        try {
            clientDao.add(client);
            response.sendRedirect("/");
        } finally {
            em.close();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManagerFactory emf = (EntityManagerFactory) getServletContext().getAttribute("emf");
        EntityManager em = emf.createEntityManager();
        JpaDAO clientDao = new ClientService(em);
        try{
            List<Client> clients = clientDao.getAll();
            request.setAttribute("clients",clients);
            request.getRequestDispatcher("clients.jsp").forward(request,response);
        } finally {
            em.close();
        }
    }
}
