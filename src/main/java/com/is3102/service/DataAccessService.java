package com.is3102.service;

import com.is3102.entity.Employee;
import org.primefaces.json.JSONObject;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class DataAccessService<T> {

    @PersistenceContext
    private EntityManager em;

    public DataAccessService() {
        System.out.println("this is dataaccessservice");
    }
    
    private Class<T> type;

    public DataAccessService(Class<T> type) {
        this.type = type;
    }

    public T create(T t) {
        this.em.persist(t);
        this.em.flush();
        this.em.refresh(t);
        return t;
    }

    public T find(Object id) {
        System.out.println("type of this fucking"  + this.type);
        System.out.println(id);
        System.out.println(this.em == null ? "null" : "notnull");
        return this.em.find(this.type, id);
    }

    public void delete(Object id) {
        Object ref = this.em.getReference(this.type, id);
        this.em.remove(ref);
    }

    public boolean deleteItems(T[] items) {
        for (T item : items) {
            if( item instanceof Employee){
                Employee employee = (Employee)item;
                if(employee.getId() == 1){
                    continue;
                }
            }
            em.remove(em.merge(item));
        }
        return true;
    }

    public T update(T item) {
        if( item instanceof Employee){
                Employee employee = (Employee)item;
                if(employee.getId() == 1){
                    return item;
                }
            }
        return (T) this.em.merge(item);
        
    }

    public List findWithNamedQuery(String namedQueryName) {
        return this.em.createNamedQuery(namedQueryName).getResultList();
    }

    public List findWithNamedQuery(String namedQueryName, Map parameters) {
        return findWithNamedQuery(namedQueryName, parameters, 0);
    }

    public List findWithNamedQuery(String queryName, int resultLimit) {
        return this.em.createNamedQuery(queryName).
                setMaxResults(resultLimit).
                getResultList();
    }

    public List<T> findByNativeQuery(String sql) {
        return this.em.createNativeQuery(sql, type).getResultList();
    }

    public int countTotalRecord(String namedQueryName) {
        Query query = em.createNamedQuery(namedQueryName);
        Number result = (Number) query.getSingleResult();
        return result.intValue();
    }

    public List findWithNamedQuery(String namedQueryName, Map parameters, int resultLimit) {
        Set<Map.Entry<String, Object>> rawParameters = parameters.entrySet();
        Query query = this.em.createNamedQuery(namedQueryName);
        if (resultLimit > 0) {
            query.setMaxResults(resultLimit);
        }
        for (Map.Entry<String, Object> entry : rawParameters) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
        return query.getResultList();
    }

    public List findWithNamedQuery(String namedQueryName, int start, int end) {
        System.out.println("namedQueryName " + namedQueryName );
        System.out.println("start " + start);
        System.out.println("end " + end);

        Query query = this.em.createNamedQuery(namedQueryName);
        query.setMaxResults(end - start);
        query.setFirstResult(start);
        return query.getResultList();
    }

    public List findWithNamedQuery(String namedQueryName, Map parameters, int start, int end) {
        System.out.println("namedQueryName " + namedQueryName );
        System.out.println("start " + start);
        System.out.println("end " + end);

        Set<Map.Entry<String, Object>> rawParameters = parameters.entrySet();
        Query query = this.em.createNamedQuery(namedQueryName);
        if (end >= start) {
            query.setMaxResults(end - start);
            query.setFirstResult(start);
        }
        for (Map.Entry<String, Object> entry : rawParameters) {
            query.setParameter(entry.getKey(), entry.getValue());
        }

        return query.getResultList();
    }
}