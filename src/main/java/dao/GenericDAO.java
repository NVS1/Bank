package dao;

import java.util.List;

public interface GenericDAO <Entity, ID>{
    void add (Entity entity);
    Entity get (ID id);
    List<Entity> getAll();
}
