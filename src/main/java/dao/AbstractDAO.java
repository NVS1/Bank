package dao;

import javax.persistence.EntityManager;
import java.util.List;

public abstract class AbstractDAO <Entity, Key>{
    protected EntityManager em;

    public AbstractDAO(EntityManager em) {
        this.em = em;
    }

    void create (Entity entity){
        em.getTransaction().begin();
        try {
            em.persist(entity);
            em.getTransaction().commit();
        } catch (Exception ex){
            em.getTransaction().rollback();
            throw new RuntimeException(ex);
        }
    }
    abstract Entity get(Key key);
    abstract List<Entity> getAll();
}
