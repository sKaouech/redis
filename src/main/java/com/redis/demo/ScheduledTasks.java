package com.redis.demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.redis.util.RedisLockRegistry;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.locks.Lock;

@Component
public class ScheduledTasks {

    @Autowired
    private RedisLockRegistry redisLockRegistry;

    @Scheduled(cron = "0 0/5 * * * ?")
    public void scheduledTask() {
        Lock lock = redisLockRegistry.obtain("scheduledTaskName");
        boolean acquired = false;
        try {
            acquired = lock.tryLock();
            if (acquired) {
                // votre logique de tâche planifiée
            }
        } finally {
            if (acquired) {
                lock.unlock();
            }
        }
    }
}
