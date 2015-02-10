package study.gordon.titan.sys.user.dao;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import study.gordon.titan.sys.user.entity.Role;
import study.gordon.titan.sys.user.repository.RoleDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-test.xml" })
public class RoleDaoTest {

    @Resource
    private RoleDao roleDao;

    // @Test
    @Rollback(false)
    public final void prepareData() {
        Role role = new Role();
        role.setName("ROLE 01");
        role.setRole("role01");
        roleDao.save(role);

        role = new Role();
        role.setName("ROLE 02");
        role.setRole("role02");
        roleDao.save(role);

        role = new Role();
        role.setName("ROLE 03");
        role.setRole("role03");
        roleDao.save(role);

        role = new Role();
        role.setName("ROLE 04");
        role.setRole("role04");
        roleDao.save(role);

        role = new Role();
        role.setName("ROLE 05");
        role.setRole("role05");
        roleDao.save(role);
    }

    @Test
    public final void nothing() {

    }
}
