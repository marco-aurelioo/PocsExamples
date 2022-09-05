package com.semaphore.redis.PocSemaphoreRedis.app.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class JobViewController {

    @GetMapping()
    fun getJobReport(): String{
        return " OK"
    }


}