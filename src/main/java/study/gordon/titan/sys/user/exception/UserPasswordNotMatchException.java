package study.gordon.titan.sys.user.exception;

public class UserPasswordNotMatchException extends UserException {

    private static final long serialVersionUID = -3568192331709704429L;

    public UserPasswordNotMatchException() {
        super("user.password.not.match", null);
    }
}
