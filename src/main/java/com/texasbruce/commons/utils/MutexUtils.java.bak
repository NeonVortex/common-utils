package com.texasbruce.commons.utils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.texasbruce.commons.datatypes.Mutex;

public class MutexUtils {

    public static int DEFAULT_WAIT_COUNT = 1000;
    public static int DEFAULT_WAIT_INTERVAL = 10;

    
    /**
     * Lock forever until specifically release (unlock) or timeout
    * Must provide an optimized waitMs value.(e.g. If many threads are running, 
    * provide big waitMs value e.g. 1000000 ms = 100 threads * 10s per thread allowance)
    * @param mm
    * @param uniqueKeyValue
    * @param waitMs if null provided, it will be default valule 100000 = 10s
    * @throws Mutex.WaitTimeoutException
    * @throws InterruptedException
    */
    public static <T> void lock (ConcurrentHashMap<T, Mutex<Object>> mm, T uniqueKeyValue, Long waitMs) throws Mutex.WaitTimeoutException, InterruptedException {

        if (waitMs == null) {
            waitMs = new Long(DEFAULT_WAIT_COUNT * DEFAULT_WAIT_INTERVAL);
        }

        synchronized ( MapUtils.putIfAbsentRetNewVal(mm, uniqueKeyValue, new Mutex<Object>()) ) {

            Mutex<Object> m = MapUtils.putIfAbsentRetNewVal(mm, uniqueKeyValue, new Mutex<Object>());

            if (m.getLockCount() == 0) {
                m.incrementLockCount();
            }
            else {
                m.incrementLockCount();

                m.wait(waitMs);

                if (m.getValue() == null) {
                    throw new Mutex.WaitTimeoutException();
                }
                else {
                    m.setValue(null);
                }
            }
        }

    }

    /**
     * Unlock must be after lock, and must provide a NON-null psudo value (e.g. new Object(), 0L)
     * @param mm
     * @param uniqueKeyValue1
     * @param value
     * @throws Mutex.UnlockNonExistingLockException
     * @throws Mutex.UnlockMutexWithoutValueException
     */
    public static <T> void unlock (ConcurrentHashMap<T, Mutex<Object>> mm, T uniqueKeyValue1) throws Mutex.UnlockNonExistingLockException {
        
        if (mm.get(uniqueKeyValue1) == null) {
            throw new Mutex.UnlockNonExistingLockException();
        }
        else {
            synchronized (mm.get(uniqueKeyValue1)) {
                Mutex<Object> m = mm.get(uniqueKeyValue1);
                m.decrementLockCount();
                
                if (m.getLockCount() <= 0) {
                    mm.remove(uniqueKeyValue1);
                }
                else {
                    m.setValue(new Object());

                    m.notify();
                }
            }
        }


    }
    

    
    /**
     * Lock until a value is populated in the Mutex or timeout
     * valueMap.get(valueMapKey) must be type of V
     * @param valueMap
     * @param valueMapKey
     * @param mutexMap
     * @param uniqueKeyValue
     * @param waitMs if null provided, it will be default valule 100000 = 10s
     * @throws Mutex.WaitTimeoutException
     * @throws InterruptedException
     */
    public static <T,V> void lockUntilValue (ConcurrentHashMap<T, Mutex<V>> mutexMap, T uniqueKeyValue, Map<Object,Object> valueMap, String valueMapKey, Long waitMs) throws Mutex.WaitTimeoutException, InterruptedException {

        if (waitMs == null) {
            waitMs = new Long(DEFAULT_WAIT_COUNT * DEFAULT_WAIT_INTERVAL);
        }

        synchronized (MapUtils.putIfAbsentRetNewVal(mutexMap, uniqueKeyValue, new Mutex<V>())) {

            Mutex<V> m = MapUtils.putIfAbsentRetNewVal(mutexMap, uniqueKeyValue, new Mutex<V>());
            
            if (m.getLockCount() == 0) {
                m.incrementLockCount();
            }
            else {
                m.incrementLockCount();
                
                m.wait(waitMs);
                
                if (m.getValue() == null) {
                    throw new Mutex.WaitTimeoutException();
                }
                else {
                    MapUtils.putIfAbsentRetNewVal(valueMap, valueMapKey, m.getValue());
                }
            }
        }

    }

    @SuppressWarnings("unchecked")
    public static <T,V> void unlockUpdateValue (ConcurrentHashMap<T, Mutex<V>> mm, T uniqueKeyValue1, Map<Object,Object> valueContainer, Object valueContainerKeyName) throws Mutex.UnlockNonExistingLockException, Mutex.UnlockMutexWithoutValueException {
        
        if (mm.get(uniqueKeyValue1) == null) {
            throw new Mutex.UnlockNonExistingLockException();
        }
        else {
            synchronized (mm.get(uniqueKeyValue1)) {
                Mutex<V> m = mm.get(uniqueKeyValue1);
                
                m.decrementLockCount();
                
                if (m.getLockCount() <= 0) {
                    mm.remove(uniqueKeyValue1);
                }
                else {
                    V id = (V)valueContainer.get(valueContainerKeyName);
                    
                    if (id != null) {
                        m.setValue(id);

                        m.notifyAll();
                    }
                    else  {
                        throw new Mutex.UnlockMutexWithoutValueException();
                    }
                }
            }
        }
        
    }


    public static <T,V> boolean isLockedForValue (ConcurrentHashMap<T, Mutex<V>> mm, T uniqueKeyValue1) {
       synchronized (mm) {
           return (mm.get(uniqueKeyValue1) != null && mm.get(uniqueKeyValue1).getLockCount() > 0) ;
       }
    }
}
