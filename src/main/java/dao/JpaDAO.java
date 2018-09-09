package dao;

import javax.persistence.EntityManager;
import java.util.List;

public abstract class JpaDAO<Entity,ID> implements GenericDAO<Entity, ID> {
    private EntityManager em;
    private Class<?> persistentClass;

    public JpaDAO(EntityManager em, Class<?> persistentClass) {
        this.em = em;
        this.persistentClass = persistentClass;
    }

    protected EntityManager getEm() {
        return em;
    }

    protected Class<?> getPersistentClass() {
        return persistentClass;
    }

    @Override
    public void add(Entity entity) {
        em.getTransaction().begin();
        try {
            em.persist(entity);
            em.getTransaction().commit();
        } catch (Exception ex){
            em.getTransaction().rollback();
            throw new RuntimeException(ex);
        }
    }

    @Override
    public Entity get(ID id) {
       return (Entity) getEm().find(getPersistentClass(),id);
    }

    @Override
    public List<Entity> getAll() {
        return getEm()
                .createQuery("SELECT x FROM "+getPersistentClass().getSimpleName()+" x")
                .getResultList();
    }
}
