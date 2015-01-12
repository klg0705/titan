package study.gordon.titan.showcase.easy.service;

import study.gordon.titan.common.service.BaseService;
import study.gordon.titan.showcase.easy.entity.EasyEntity;

public interface EasyEntityService extends BaseService<EasyEntity, Long>{

    EasyEntity findByName(String name);
}
