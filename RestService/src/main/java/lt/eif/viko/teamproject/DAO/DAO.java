/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.eif.viko.teamproject.DAO;

import java.util.List;

/**
 * This is DAO interface
 * @author Tomas
 */
public interface DAO<T> {
    
    /**
     * load all objects
     *
     * @return an objects list
     */
    List<T> load();

    /**
     * get an object
     *
     * @return an object
     */
    T get(Object object);

    /**
     * insert object
     *
     */
    void insert(T object);

    /**
     * updates object
     *
     */
    void update(T object);

    /**
     * deletes object
     *
     */
    void delete(T object);
    
}
