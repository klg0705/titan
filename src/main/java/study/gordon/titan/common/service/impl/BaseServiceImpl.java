package study.gordon.titan.common.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import study.gordon.titan.common.dao.BaseDao;
import study.gordon.titan.common.entity.BaseEntity;
import study.gordon.titan.common.service.BaseService;

@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public abstract class BaseServiceImpl<E extends BaseEntity<K>, K extends Serializable> implements BaseService<E, K> {

    @Resource
    protected BaseDao<E, K> baseDao;

    public E findById(K id) {
        return baseDao.findById(id);
    }

    public List<E> getAll() {
        return baseDao.getAll();
    }

    public long count() {
        return baseDao.count();
    }

    @Transactional
    public void create(E entity) {
        baseDao.create(entity);
    }

    @Transactional
    public void update(E entity) {
        baseDao.update(entity);
    }

    @Transactional
    public void delete(E entity) {
        baseDao.delete(entity);
    }

    @Transactional
    public void deleteById(K id) {
        baseDao.deleteById(id);
    }

}
