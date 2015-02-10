package study.gordon.titan.sys.user.repository;

import study.gordon.titan.common.dao.BaseRepository;
import study.gordon.titan.sys.user.entity.User;

public interface UserDao extends BaseRepository<User, Long> {

    User findByUsername(String username);

    User findByMobilePhoneNumber(String mobilePhoneNumber);

    User findByEmail(String email);

}
