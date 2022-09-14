package com.semaphore.redis.PocSemaphoreRedis.app.controller

import org.redisson.api.RSemaphore
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class JobViewController(
    val semaphore: RSemaphore
) {

    @GetMapping("/torneira")
    fun getJobReport(): Int{
        return semaphore.availablePermits()
    }

    @PostMapping("/torneira")
    fun postTorneria(@RequestBody adiciona: Int){
        println("abrindo a torneira em ${adiciona} permissions")
        semaphore.addPermits(adiciona)
        println("total de ${semaphore.availablePermits()} permissions")
    }

    @PostMapping("/torneira/close")
    fun postFechaTorneria(@RequestBody fecha: Boolean){
        semaphore.drainPermits()
        println("total de ${semaphore.availablePermits()} permissions depois drain")

    }


}