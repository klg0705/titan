package study.gordon.titan.showcase.simple.dao.impl;

import org.springframework.stereotype.Repository;

import study.gordon.titan.common.dao.impl.BaseDaoImpl;
import study.gordon.titan.showcase.simple.dao.SimpleEntityDao;
import study.gordon.titan.showcase.simple.entity.SimpleEntity;

@Repository
public class SimpleEntityDaoImpl extends BaseDaoImpl<SimpleEntity, Long> implements SimpleEntityDao {

}
