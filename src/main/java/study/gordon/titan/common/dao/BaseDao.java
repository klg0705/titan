package study.gordon.titan.common.dao;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;

import study.gordon.titan.common.entity.BaseEntity;
import study.gordon.titan.common.entity.QueryResult;

public interface BaseDao<E extends BaseEntity<K>, K extends Serializable> {

    E findById(K id);

    List<E> getAll();

    long count();

    void create(E entity);

    void update(E entity);

    void delete(E entity);

    void deleteById(K id);

    QueryResult<E> getScrollData(int firstindex, int maxresult, String wherejpql, Object[] queryParams,
            LinkedHashMap<String, String> orderby);

    QueryResult<E> getScrollData(int firstindex, int maxresult, String wherejpql, Object[] queryParams);

    QueryResult<E> getScrollData(int firstindex, int maxresult, LinkedHashMap<String, String> orderby);

    QueryResult<E> getScrollData(int firstindex, int maxresult);

}
