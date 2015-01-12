package study.gordon.titan.showcase.easy.repository;

import study.gordon.titan.common.dao.BaseRepository;
import study.gordon.titan.showcase.easy.entity.EasyEntity;

public interface EasyEntityDao extends BaseRepository<EasyEntity, Long>, EasyEntityDaoCustom {

    EasyEntity findByName(String name);
}
