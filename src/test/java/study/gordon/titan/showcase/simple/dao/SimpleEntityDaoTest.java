package study.gordon.titan.showcase.simple.dao;

import java.util.Date;
import java.util.LinkedHashMap;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import study.gordon.titan.common.entity.QueryResult;
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

    @Test
    public final void testPagination() {
        long count = simpleEntityDao.count();
        System.out.println(count);

        QueryResult<SimpleEntity> result = simpleEntityDao.getScrollData(0, 2);
        for (SimpleEntity simple : result.getResultList()) {
            System.out.println(simple.getId());
        }
        System.out.println();

        LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
        orderby.put("id", "desc");
        result = simpleEntityDao.getScrollData(4, 4, orderby);
        for (SimpleEntity simple : result.getResultList()) {
            System.out.println(simple.getId());
        }
        System.out.println();

    }

}
