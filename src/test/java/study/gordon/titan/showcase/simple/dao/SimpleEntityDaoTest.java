package study.gordon.titan.showcase.simple.dao;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import study.gordon.titan.showcase.simple.entity.Gender;
import study.gordon.titan.showcase.simple.entity.SimpleEntity;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-test.xml" })
@Transactional
public class SimpleEntityDaoTest {

    @Resource
    private SimpleEntityDao simpleEntityDao;

    @Test
    @Rollback(false)
    public final void simpleTest() {
        SimpleEntity simpleEntity = new SimpleEntity();
        simpleEntity.setAge(22);
        simpleEntity.setBirthday(new Date());
        simpleEntity.setName("Admin");
        simpleEntity.setGender(Gender.MALE);
        simpleEntity.setShow(true);

        simpleEntityDao.create(simpleEntity);

        simpleEntity.setName("Guest");
        simpleEntityDao.update(simpleEntity);

        simpleEntityDao.delete(simpleEntity);
    }

}
