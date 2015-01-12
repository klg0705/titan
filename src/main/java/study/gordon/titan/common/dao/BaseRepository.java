package study.gordon.titan.common.dao;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import study.gordon.titan.common.entity.BaseEntity;

@NoRepositoryBean
public interface BaseRepository<E extends BaseEntity<K>, K extends Serializable> extends JpaRepository<E, K> {

}
