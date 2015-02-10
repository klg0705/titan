package study.gordon.titan.sys.user.exception;

import study.gordon.titan.common.exception.BaseException;

public class UserException extends BaseException {

    private static final long serialVersionUID = 8280315980513687198L;

    public UserException(String code, Object[] args) {
        super("user", code, args, null);
    }
}
