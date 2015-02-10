package study.gordon.titan.sys.user.exception;

public class UserPasswordRetryLimitExceedException extends UserException {

    private static final long serialVersionUID = 3193499042878093694L;

    public UserPasswordRetryLimitExceedException(int retryLimitCount) {
        super("user.password.retry.limit.exceed", new Object[] { retryLimitCount });
    }
}
