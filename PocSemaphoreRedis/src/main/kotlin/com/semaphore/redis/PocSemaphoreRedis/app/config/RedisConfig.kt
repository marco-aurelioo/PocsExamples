package com.semaphore.redis.PocSemaphoreRedis.app.config

import org.redisson.Redisson
import org.redisson.api.RPermitExpirableSemaphore
import org.redisson.api.RSemaphore
import org.redisson.api.RedissonClient
import org.redisson.config.Config
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.Duration

@Configuration
class RedisConfig {

    @Bean
    fun redissonConfig(): RSemaphore {
        val config = Config()
        config
            .useSingleServer()
                .setAddress("redis://localhost:6379")
                .setPassword("eYVX7EwVmmxKPCDmwMtyKVge8oLd2t81")

        val semaphore =  Redisson.create(config)
            .getSemaphore("SemaphoreExample")
        println( semaphore.drainPermits())
        println( semaphore.availablePermits())
        println( semaphore.addPermits(4))
        return semaphore
    }

}