package com.module.dao.repository.auth;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by zxz on 2015/11/13.
 */

public interface GenericDao<T,ID extends Serializable> {
    /**
     * Method that returns the number of entries from a table that meet some
     * criteria (where clause params)
     *
     * @param params sql parameters
     * @return the number of records meeting the criteria
     */
    long countAll(Map<String, Object> params);


    /**
     * Method that returns the entity of entries from a table
     * @return
     */
    List<T> findByModule(Map<String, Object> params,Integer start,Integer pageSize);

    /**
     *Method that returns the entity of entries from a table that meet the id
     *
     * @param id
     * @return
     */
    T findById(Object id);

    /**
     * Method that creat entity  from a table that Transfered
     * @param t
     * @return
     */
    T create(T t);

    /**
     * Method that delete entity  from a table that meet the id
     * @param id
     * @return
     */
    void delete(Object id);

    /**
     * Method that update entity  from a table that Transfered
     * @param t
     * @return
     */
    T update(T t);
}