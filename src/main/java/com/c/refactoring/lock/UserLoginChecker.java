package com.c.refactoring.lock;

import static java.util.Objects.isNull;

import java.util.Date;
import java.util.List;

public class UserLoginChecker {

    public static final int LOCK_DURATION = (60*60*1000);

    /**
     * {@inheritDoc}.
     */
    public Lock isUserAllowedToLogin(long id, String status,
            boolean isFirstScreen, User newUser, List activeUsers) {
        if(activeUsers.isEmpty() || isNull(activeUsers.get(0))) {
            return createOpenLock();
        }

        final Object[] activeUser = (Object[]) activeUsers.get(0);
        final String activeUserId = (String) activeUser[0];
        if (activeUserId == null) return createOpenLock();

        final Date lockTimestamp = (Date) activeUser[1];
        final boolean isSameUser = activeUserId.equalsIgnoreCase(newUser.getUserId());
        final boolean isLockTimedOutAtFirstScreen = isFirstScreen && (new Date()).getTime() - lockTimestamp.getTime() > LOCK_DURATION;

        if (isSameUser || isLockTimedOutAtFirstScreen) {
            return createOpenLock();
        } else {
            return createClosedLock(activeUserId);
        }
    }

    private Lock createClosedLock(String activeUserId) {
        String lockMsg = Constants.LOCK_TEXT.replaceAll("@@USER@@", activeUserId);
        Lock lck = new Lock();
        lck.setRead(true);
        lck.setLockReason(lockMsg);
        return lck;
    }

    private Lock createOpenLock() {
        Lock lck = new Lock();
        lck.setRead(false);
        return lck;
    }
}