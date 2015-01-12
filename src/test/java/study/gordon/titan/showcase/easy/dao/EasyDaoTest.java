package study.gordon.titan.showcase.easy.dao;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import study.gordon.titan.showcase.easy.entity.EasyEntity;
import study.gordon.titan.showcase.easy.entity.Gender;
import study.gordon.titan.showcase.easy.repository.EasyEntityDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-test.xml" })
@Transactional
public class EasyDaoTest {

    @Resource
    private EasyEntityDao easyDao;

    // @Test
    @Rollback(false)
    public final void prepareData() {
        easyDao.deleteAll();

        EasyEntity easyEntity = new EasyEntity();
        easyEntity.setName("name1");
        easyEntity.setAge(14);
        easyDao.save(easyEntity);

        easyEntity = new EasyEntity();
        easyEntity.setName("name2");
        easyEntity.setAge(14);
        easyDao.save(easyEntity);

        easyEntity = new EasyEntity();
        easyEntity.setName("name3");
        easyEntity.setAge(14);
        easyDao.save(easyEntity);

        easyEntity = new EasyEntity();
        easyEntity.setName("name4");
        easyEntity.setAge(14);
        easyDao.save(easyEntity);

        easyEntity = new EasyEntity();
        easyEntity.setName("name1");
        easyEntity.setAge(14);
        easyDao.save(easyEntity);
    }

    @Test
    @Rollback(true)
    public final void simpleTest() {
        EasyEntity easyEntity = new EasyEntity();
        easyEntity.setAge(22);
        easyEntity.setBirthday(new Date());
        easyEntity.setName("Admin");
        easyEntity.setGender(Gender.MALE);
        easyEntity.setShow(true);
        easyDao.save(easyEntity);
        assertNotNull(easyEntity.getId());

        easyEntity.setName("Guest");
        easyDao.save(easyEntity);

        EasyEntity found = easyDao.findOne(easyEntity.getId());
        assertEquals("Guest", found.getName());

        assertEquals(6, easyDao.count());

        easyDao.delete(easyEntity);

        assertEquals(5, easyDao.count());
    }

    @Test
    public final void testPagination() {
        Sort sort = new Sort(Direction.DESC, "id");
        PageRequest pageRequest = new PageRequest(1, 3, sort);
        Page<EasyEntity> page = easyDao.findAll(pageRequest);

        assertEquals(1, page.getNumber());
        assertEquals(2, page.getNumberOfElements());
        assertEquals(3, page.getSize());
        assertEquals(5, page.getTotalElements());
        assertEquals(2, page.getTotalPages());
        assertTrue(page.hasContent());
        assertFalse(page.hasNext());
        assertTrue(page.hasPrevious());
        assertFalse(page.isFirst());
        assertTrue(page.isLast());

        List<EasyEntity> easyList = page.getContent();
        assertEquals("name2", easyList.get(0).getName());
        assertEquals("name1", easyList.get(1).getName());
    }

    @Test
    public final void testFindByName() {
        EasyEntity easy = easyDao.findByName("name2");
        assertEquals("name2", easy.getName());

        easy = easyDao.findByName("xxxxx");
        assertNull(easy);

        try {
            easy = easyDao.findByName("name1");
            fail("name is not unique!");
        } catch (Exception e) {
        }
    }

    @Test
    public final void testNothingToDo() {
        assertEquals(9, easyDao.nothingToDo());
    }

}
