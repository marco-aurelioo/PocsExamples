package com.semaphore.redis.PocSemaphoreRedis.app.job

import org.redisson.api.RPermitExpirableSemaphore
import org.redisson.api.RSemaphore
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.util.concurrent.TimeUnit
import kotlin.random.Random
import kotlin.random.nextLong


@Component
class JobExample(
    val semaphore: RSemaphore
) {

    @Scheduled(fixedDelay = 5000)
    fun jobExecution(){
        for (i in 1..10) {
            val threadExample = ThreadExample(semaphore)
            threadExample.start()
        }

    }

}

class ThreadExample(val semaphore: RSemaphore): Thread() {

    override fun run(){
        val keySem = semaphore.tryAcquire(1,10,TimeUnit.SECONDS)
        if(keySem) {
            try {
                println("${this.name} pedindo acesso !!")
                println("ainda restam ${semaphore.availablePermits()}.")
                sleep(Random.nextLong(LongRange(0,5)) * 1000)
            }finally {
                semaphore.release(1)
                println("${this.name} liberando acesso !!")
            }
        }
    }

}