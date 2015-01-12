package study.gordon.titan.common.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import study.gordon.titan.common.entity.BaseEntity;

public interface BaseService<E extends BaseEntity<K>, K extends Serializable> {

    E findById(K id);

    List<E> getAll();

    long count();

    void create(E entity);

    void update(E entity);

    void delete(E entity);

    void deleteById(K id);

    Page<E> findAll(int pageNum, int pageSize, Sort sort);

}
