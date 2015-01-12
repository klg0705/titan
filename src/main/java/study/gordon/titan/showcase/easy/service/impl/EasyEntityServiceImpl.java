package study.gordon.titan.showcase.easy.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import study.gordon.titan.common.service.impl.BaseServiceImpl;
import study.gordon.titan.showcase.easy.entity.EasyEntity;
import study.gordon.titan.showcase.easy.repository.EasyEntityDao;
import study.gordon.titan.showcase.easy.service.EasyEntityService;

@Service
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class EasyEntityServiceImpl extends BaseServiceImpl<EasyEntity, Long> implements EasyEntityService {

    public EasyEntity findByName(String name) {
        return ((EasyEntityDao) baseRepository).findByName(name);
    }
}
