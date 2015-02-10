package study.gordon.titan.sys.user.service;

import org.springframework.stereotype.Service;

import study.gordon.titan.common.util.Md5Utils;
import study.gordon.titan.sys.user.entity.User;
import study.gordon.titan.sys.user.exception.UserPasswordNotMatchException;

@Service
public class PasswordService {

    public void validate(User user, String password) {
        if (!matches(user, password)) {
            throw new UserPasswordNotMatchException();
        }
    }

    public boolean matches(User user, String newPassword) {
        return user.getPassword().equals(encryptPassword(user.getUsername(), newPassword, user.getSalt()));
    }

    public String encryptPassword(String username, String password, String salt) {
        return Md5Utils.hash(username + password + salt);
    }

    public static void main(String[] args) {
        System.out.println(new PasswordService().encryptPassword("monitor", "123456", "iY71e4d123"));
    }
}
