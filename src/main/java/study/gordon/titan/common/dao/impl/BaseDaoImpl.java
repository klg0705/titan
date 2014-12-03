package study.gordon.titan.common.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;

import study.gordon.titan.common.dao.BaseDao;
import study.gordon.titan.common.entity.BaseEntity;

public abstract class BaseDaoImpl<E extends BaseEntity<K>, K extends Serializable> implements BaseDao<E, K> {

    @Resource
    protected SessionFactory sessionFactory;

    protected Class<E> entityClass;

    @SuppressWarnings("unchecked")
    public BaseDaoImpl() {
        ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
        this.entityClass = (Class<E>) genericSuperclass.getActualTypeArguments()[0];
    }

    @SuppressWarnings("unchecked")
    public E findById(K id) {
        return (E) sessionFactory.getCurrentSession().get(entityClass, id);
    }

    @SuppressWarnings("unchecked")
    public List<E> getAll() {
        return sessionFactory.getCurrentSession().createQuery("from " + entityClass.getName() + " x").list();
    }

    public long count() {
        Number n = (Number) sessionFactory.getCurrentSession()
                .createQuery("select count(*) from " + entityClass.getName() + " x").uniqueResult();
        return n.longValue();
    }

    public void create(E entity) {
        sessionFactory.getCurrentSession().save(entity);
    }

    public void update(E entity) {
        sessionFactory.getCurrentSession().update(entity);
    }

    public void delete(E entity) {
        sessionFactory.getCurrentSession().delete(entity);
    }

    public void deleteById(K id) {
        delete(findById(id));
    }

}