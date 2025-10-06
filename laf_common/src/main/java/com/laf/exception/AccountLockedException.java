package com.laf.exception;

/**
 * 账号锁定异常
 */
public class AccountLockedException extends BaseException {
    public AccountLockedException() {
        super("Account is locked");
    }

    public AccountLockedException(String msg) {
        super(msg);
    }
}
