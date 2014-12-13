package study.gordon.titan.showcase.simple.service;

import java.util.Date;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import study.gordon.titan.showcase.simple.entity.Gender;
import study.gordon.titan.showcase.simple.entity.SimpleEntity;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-test.xml" })
public class SimpleEntityServiceTest {

    @Resource
    private SimpleEntityService simpleEntityService;

    @Test
    @Rollback(false)
    @Transactional
    public final void simpleTest() {
        SimpleEntity simpleEntity = new SimpleEntity();
        simpleEntity.setAge(22);
        simpleEntity.setBirthday(new Date());
        simpleEntity.setName("Admin");
        simpleEntity.setGender(Gender.MALE);
        simpleEntity.setShow(true);

        simpleEntityService.create(simpleEntity);

        simpleEntity = new SimpleEntity();
        simpleEntity.setName("Test");
        simpleEntity.setAge(44);
        simpleEntityService.create(simpleEntity);

        simpleEntity.setName("Guest");
        simpleEntity.setAge(33);
        simpleEntityService.update(simpleEntity);

        simpleEntityService.delete(simpleEntity);

        simpleEntity = simpleEntityService.findById(2L);
        System.out.println(simpleEntity.getBirthday());
    }

}
