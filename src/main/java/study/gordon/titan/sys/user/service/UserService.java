package study.gordon.titan.sys.user.service;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import study.gordon.titan.common.service.impl.BaseServiceImpl;
import study.gordon.titan.sys.user.entity.User;
import study.gordon.titan.sys.user.repository.UserDao;

@Service
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class UserService extends BaseServiceImpl<User, Long> {

    @Resource
    PasswordService passwordService;

    private UserDao getUserDao() {
        return (UserDao) baseRepository;
    }

    @Transactional
    public void create(User user) {
        if (user.getCreateDate() == null) {
            user.setCreateDate(new Date());
        }
        user.randomSalt();
        user.setPassword(passwordService.encryptPassword(user.getUsername(), user.getPassword(), user.getSalt()));

        super.create(user);
    }

    public User findByUsername(String username) {
        if (StringUtils.isEmpty(username)) {
            return null;
        }
        return getUserDao().findByUsername(username);
    }

    public User findByEmail(String email) {
        if (StringUtils.isEmpty(email)) {
            return null;
        }
        return getUserDao().findByEmail(email);
    }

    public User findByMobilePhoneNumber(String mobilePhoneNumber) {
        if (StringUtils.isEmpty(mobilePhoneNumber)) {
            return null;
        }
        return getUserDao().findByMobilePhoneNumber(mobilePhoneNumber);
    }

}
