package study.gordon.titan.sys.user.exception;

public class UserNotExistsException extends UserException {

    private static final long serialVersionUID = -1515520665128268803L;

    public UserNotExistsException() {
        super("user.not.exists", null);
    }
}
