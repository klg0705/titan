package study.gordon.titan.common.dao.impl;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.LinkedHashMap;
import java.util.List;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import study.gordon.titan.common.dao.BaseDao;
import study.gordon.titan.common.entity.BaseEntity;
import study.gordon.titan.common.entity.QueryResult;

public abstract class BaseDaoImpl<E extends BaseEntity<K>, K extends Serializable> implements BaseDao<E, K> {

    @PersistenceContext
    protected EntityManager em;

    protected Class<E> entityClass;

    @SuppressWarnings("unchecked")
    public BaseDaoImpl() {
        ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
        this.entityClass = (Class<E>) genericSuperclass.getActualTypeArguments()[0];
    }

    public E findById(K id) {
        return (E) em.find(entityClass, id);
    }

    @SuppressWarnings("unchecked")
    public List<E> getAll() {
        return em.createQuery("from " + getEntityName(entityClass) + " x").getResultList();
    }

    public long count() {
        Number n = (Number) em.createQuery(
                "select count(" + getCountField(entityClass) + ") from " + getEntityName(entityClass) + " o")
                .getSingleResult();
        return n.longValue();
    }

    public void create(E entity) {
        em.persist(entity);
    }

    public void update(E entity) {
        em.merge(entity);
    }

    public void delete(E entity) {
        em.remove(entity);
    }

    public void deleteById(K id) {
        delete(em.getReference(entityClass, id));
    }

    @SuppressWarnings("unchecked")
    @Override
    public QueryResult<E> getScrollData(int firstindex, int maxresult, String wherejpql, Object[] queryParams,
            LinkedHashMap<String, String> orderby) {
        QueryResult<E> qr = new QueryResult<E>();
        String entityname = getEntityName(entityClass);
        Query query = em.createQuery("select o from " + entityname + " o "
                + (wherejpql == null ? "" : "where " + wherejpql) + buildOrderby(orderby));
        setQueryParams(query, queryParams);
        if (firstindex != -1 && maxresult != -1) {
            query.setFirstResult(firstindex).setMaxResults(maxresult);
        }
        qr.setResultList((List<E>) query.getResultList());
        query = em.createQuery("select count(" + getCountField(entityClass) + ") from " + entityname + " o "
                + (wherejpql == null ? "" : "where " + wherejpql));
        setQueryParams(query, queryParams);
        qr.setTotalRecord((Long) query.getSingleResult());
        return qr;
    }

    @Override
    public QueryResult<E> getScrollData(int firstindex, int maxresult, String wherejpql, Object[] queryParams) {
        return getScrollData(firstindex, maxresult, wherejpql, queryParams, null);
    }

    @Override
    public QueryResult<E> getScrollData(int firstindex, int maxresult, LinkedHashMap<String, String> orderby) {
        return getScrollData(firstindex, maxresult, null, null, orderby);
    }

    @Override
    public QueryResult<E> getScrollData(int firstindex, int maxresult) {
        return getScrollData(firstindex, maxresult, null, null, null);
    }

    protected void setQueryParams(Query query, Object[] queryParams) {
        if (queryParams != null && queryParams.length > 0) {
            for (int i = 0; i < queryParams.length; i++) {
                query.setParameter(i + 1, queryParams[i]);
            }
        }
    }

    protected String buildOrderby(LinkedHashMap<String, String> orderby) {
        StringBuffer orderbyql = new StringBuffer("");
        if (orderby != null && orderby.size() > 0) {
            orderbyql.append(" order by ");
            for (String key : orderby.keySet()) {
                orderbyql.append("o.").append(key).append(" ").append(orderby.get(key)).append(",");
            }
            orderbyql.deleteCharAt(orderbyql.length() - 1);
        }
        return orderbyql.toString();
    }

    protected <T> String getEntityName(Class<T> entityClass) {
        String entityname = entityClass.getSimpleName();
        Entity entity = entityClass.getAnnotation(Entity.class);
        if (entity.name() != null && !"".equals(entity.name())) {
            entityname = entity.name();
        }
        return entityname;
    }

    protected <T> String getCountField(Class<T> clazz) {
        String out = "o";
        try {
            PropertyDescriptor[] propertyDescriptors = Introspector.getBeanInfo(clazz).getPropertyDescriptors();
            for (PropertyDescriptor propertydesc : propertyDescriptors) {
                Method method = propertydesc.getReadMethod();
                if (method != null && method.isAnnotationPresent(EmbeddedId.class)) {
                    PropertyDescriptor[] ps = Introspector.getBeanInfo(propertydesc.getPropertyType())
                            .getPropertyDescriptors();
                    out = "o." + propertydesc.getName() + "."
                            + (!ps[1].getName().equals("class") ? ps[1].getName() : ps[0].getName());
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out;
    }

}