package service;

import dao.JpaDAO;
import model.Client;

import javax.persistence.EntityManager;

public class ClientService extends JpaDAO<Client, String> {

    public ClientService(EntityManager em) {
        super(em, Client.class);
    }
}
