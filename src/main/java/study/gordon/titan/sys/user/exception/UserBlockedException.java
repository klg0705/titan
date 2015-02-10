package study.gordon.titan.sys.user.exception;

public class UserBlockedException extends UserException {

    private static final long serialVersionUID = 2363065359160933790L;

    public UserBlockedException(String reason) {
        super("user.blocked", new Object[] { reason });
    }
}
