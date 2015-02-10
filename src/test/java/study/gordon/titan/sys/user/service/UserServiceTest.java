package study.gordon.titan.sys.user.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import study.gordon.titan.sys.user.entity.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-test.xml" })
@Transactional
public class UserServiceTest {

    @Resource
    private UserService userService;

    // @Test
    @Rollback(false)
    public final void prepareData() {
        User user = new User();
        user.setUsername("user001");
        user.setEmail("user001@163.com");
        user.setMobilePhoneNumber("13813800001");
        user.setPassword("password");
        userService.create(user);

        user = new User();
        user.setUsername("user002");
        user.setEmail("user002@163.com");
        user.setMobilePhoneNumber("13813800002");
        user.setPassword("password");
        userService.create(user);

        user = new User();
        user.setUsername("user003");
        user.setEmail("user003@163.com");
        user.setMobilePhoneNumber("13813800003");
        user.setPassword("password");
        userService.create(user);

        user = new User();
        user.setUsername("user004");
        user.setEmail("user004@163.com");
        user.setMobilePhoneNumber("13813800004");
        user.setPassword("password");
        userService.create(user);

        user = new User();
        user.setUsername("user005");
        user.setEmail("user005@163.com");
        user.setMobilePhoneNumber("13813800005");
        user.setPassword("password");
        userService.create(user);
    }

    @Test
    @Rollback(true)
    public final void simpleTest() {
        User user = new User();
        user.setUsername("user000");
        user.setEmail("user000@163.com");
        user.setMobilePhoneNumber("13813800000");
        user.setPassword("user000");
        user.setAdmin(true);
        userService.create(user);
        assertNotNull(user.getId());

        user.setUsername("Guest");
        userService.update(user);

        User found = userService.findById(user.getId());
        assertEquals("Guest", found.getUsername());

        assertEquals(6, userService.count());

        userService.delete(user);

        assertEquals(5, userService.count());
    }

    @Test
    public final void testFindByUsername() {
        User user = userService.findByUsername("user002");
        assertEquals("user002", user.getUsername());

        user = userService.findByUsername("xxxxx");
        assertNull(user);
    }
}
