package study.gordon.titan.common.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import study.gordon.titan.common.dao.BaseDao;
import study.gordon.titan.common.entity.BaseEntity;

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
        Number n = (Number) em.createQuery("select count(*) from " + getEntityName(entityClass) + " x")
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

    protected <T> String getEntityName(Class<T> entityClass) {
        String entityname = entityClass.getSimpleName();
        Entity entity = entityClass.getAnnotation(Entity.class);
        if (entity.name() != null && !"".equals(entity.name())) {
            entityname = entity.name();
        }
        return entityname;
    }
}