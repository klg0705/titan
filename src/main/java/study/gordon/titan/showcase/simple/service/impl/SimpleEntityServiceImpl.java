package study.gordon.titan.showcase.simple.service.impl;

import org.springframework.stereotype.Service;

import study.gordon.titan.common.service.impl.OldBaseServiceImpl;
import study.gordon.titan.showcase.simple.entity.SimpleEntity;
import study.gordon.titan.showcase.simple.service.SimpleEntityService;

@Service
public class SimpleEntityServiceImpl extends OldBaseServiceImpl<SimpleEntity, Long> implements SimpleEntityService {

}
