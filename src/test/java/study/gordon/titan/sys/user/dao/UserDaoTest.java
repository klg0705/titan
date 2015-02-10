package study.gordon.titan.sys.user.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import study.gordon.titan.sys.user.entity.Role;
import study.gordon.titan.sys.user.entity.User;
import study.gordon.titan.sys.user.repository.RoleDao;
import study.gordon.titan.sys.user.repository.UserDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-test.xml" })
public class UserDaoTest {

    @Resource
    private UserDao userDao;

    @Resource
    private RoleDao roleDao;

    // @Test
    @Rollback(false)
    public final void prepareData() {
        userDao.deleteAll();
    }

    @Test
    @Rollback(true)
    @Transactional
    public final void simpleTest() {
        User user = new User();
        user.setUsername("user000");
        user.setEmail("user000@163.com");
        user.setMobilePhoneNumber("13813800000");
        user.setPassword("user000");
        user.setAdmin(true);
        userDao.save(user);
        assertNotNull(user.getId());

        user.setUsername("Guest");
        userDao.save(user);

        User found = userDao.findOne(user.getId());
        assertEquals("Guest", found.getUsername());

        assertEquals(6, userDao.count());

        userDao.delete(user);

        assertEquals(5, userDao.count());
    }

    @Test
    public final void testFindByUsername() {
        User user = userDao.findByUsername("user002");
        assertEquals("user002", user.getUsername());

        user = userDao.findByUsername("xxxxx");
        assertNull(user);
    }
    
    @Test
    public final void testSetRoles() {
        User user = userDao.findByUsername("user001");
        List<Role> rolelist = roleDao.findAll();
        Set<Role> roles = new HashSet<Role>();
        for (Role role : rolelist) {
            roles.add(role);
        }
        user.setRoles(roles);
    }
}
