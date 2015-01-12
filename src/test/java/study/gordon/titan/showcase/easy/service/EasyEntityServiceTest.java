package study.gordon.titan.showcase.easy.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import study.gordon.titan.showcase.easy.entity.EasyEntity;
import study.gordon.titan.showcase.easy.entity.Gender;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-test.xml" })
public class EasyEntityServiceTest {

    @Resource
    private EasyEntityService easyService;

    @Test
    @Transactional
    public final void simpleTest() {
        EasyEntity easyEntity = new EasyEntity();
        easyEntity.setAge(22);
        easyEntity.setBirthday(new Date());
        easyEntity.setName("Admin");
        easyEntity.setGender(Gender.MALE);
        easyEntity.setShow(true);
        easyService.create(easyEntity);
        assertNotNull(easyEntity.getId());

        easyEntity.setName("Guest");
        easyService.update(easyEntity);

        EasyEntity found = easyService.findById(easyEntity.getId());
        assertEquals("Guest", found.getName());

        assertEquals(6, easyService.count());

        easyService.delete(easyEntity);

        assertEquals(5, easyService.count());
    }

    @Test
    public final void testPagination() {
        Sort sort = new Sort(Direction.DESC, "id");
        Page<EasyEntity> page = easyService.findAll(2, 3, sort);

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
        EasyEntity easy = easyService.findByName("name2");
        assertEquals("name2", easy.getName());

        easy = easyService.findByName("xxxxx");
        assertNull(easy);

        try {
            easy = easyService.findByName("name1");
            fail("name is not unique!");
        } catch (Exception e) {
        }
    }
}
