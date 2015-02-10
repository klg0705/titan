package study.gordon.titan.common.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import study.gordon.titan.common.dao.BaseRepository;
import study.gordon.titan.common.entity.BaseEntity;
import study.gordon.titan.common.service.BaseService;

@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public abstract class BaseServiceImpl<E extends BaseEntity<K>, K extends Serializable> implements BaseService<E, K> {

    @Autowired
    protected BaseRepository<E, K> baseRepository;

    public E findById(K id) {
        return baseRepository.findOne(id);
    }

    public List<E> getAll() {
        return baseRepository.findAll();
    }

    public long count() {
        return baseRepository.count();
    }

    @Transactional
    public void create(E entity) {
        baseRepository.save(entity);
    }

    @Transactional
    public void update(E entity) {
        baseRepository.save(entity);
    }

    @Transactional
    public void delete(E entity) {
        baseRepository.delete(entity);
    }

    @Transactional
    public void deleteById(K id) {
        baseRepository.delete(id);
    }

    @Override
    public Page<E> findAll(int pageNum, int pageSize, Sort sort) {
        PageRequest pageRequest = new PageRequest(pageNum - 1, pageSize, sort);
        return baseRepository.findAll(pageRequest);
    }

}
