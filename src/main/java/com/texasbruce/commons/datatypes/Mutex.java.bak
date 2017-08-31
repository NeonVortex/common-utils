package com.texasbruce.commons.datatypes;

public class Mutex <ValueType> {    
    private int lockCount = 0;
    private ValueType value = null;
    
    public synchronized void incrementLockCount () {
        lockCount++;
    }
    public synchronized void decrementLockCount () {
        lockCount--;
    }
    
    public synchronized int getLockCount() {
        return lockCount;
    }
    public synchronized void setLockCount(int lockCount) {
        this.lockCount = lockCount;
    }
    public synchronized ValueType getValue() {
        return value;
    }
    public synchronized void setValue(ValueType value) {
        this.value = value;
    }

    public static class UnlockMutexWithoutValueException extends Exception {}
    public static class WaitTimeoutException extends Exception {}
    public static class UnlockNonExistingLockException extends Exception {}

}
