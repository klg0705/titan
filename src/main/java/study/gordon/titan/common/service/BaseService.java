package study.gordon.titan.common.service;

import java.io.Serializable;
import java.util.List;

import study.gordon.titan.common.entity.BaseEntity;

public interface BaseService<E extends BaseEntity<K>, K extends Serializable> {
    
    E findById(K id);

    List<E> getAll();

    long count();

    void create(E entity);

    void update(E entity);

    void delete(E entity);

    void deleteById(K id);

}
