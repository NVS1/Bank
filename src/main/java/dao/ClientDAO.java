package dao;

import model.Client;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class ClientDAO extends AbstractDAO <Client,String> {

    public ClientDAO(EntityManager em) {
        super(em);
    }

    @Override
    Client get(String phone) {
        return em.find(Client.class, phone);
    }

    @Override
    List<Client> getAll() {
        TypedQuery<Client> query = em.createQuery("SELECT c FROM Client c",Client.class);
        return query.getResultList();
    }
}
