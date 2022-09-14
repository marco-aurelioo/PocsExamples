package com.semaphore.redis.PocSemaphoreRedis.app.job

import org.redisson.api.RPermitExpirableSemaphore
import org.redisson.api.RSemaphore
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.random.Random
import kotlin.random.nextLong


@Component
class JobExample(
    val semaphore: RSemaphore
) {

    var qeueLinks = getQeue()

    private fun getQeue(): Queue<String> {
        var queue = LinkedList<String>()
        for(i in 1 .. 115){
            queue.add("https://pokeapi.co/api/v2/pokemon?offset=${(i*10)}&limit=10" )
        }
        return queue
    }

    @Scheduled(fixedDelay = 60000)
    fun jobExecution(){
        for (i in 1..100) {
            val threadExample = ThreadExample(semaphore,qeueLinks)
            threadExample.start()
        }
    }

}

class ThreadExample(val semaphore: RSemaphore, val queue: Queue<String>): Thread() {

    val client = HttpClient.newBuilder().build();
    override fun run(){
        val keySem = semaphore.tryAcquire(1,10,TimeUnit.SECONDS)
        if(keySem) {
            try {
                val url = URI.create(queue.poll()).let {
                    println("${this.name} pedindo acesso !!")
                    println("ainda restam  ${semaphore.availablePermits()}.")
                   val request = HttpRequest.newBuilder()
                        .uri(it)
                        .GET()
                        .header("Content-Type", "application/json")
                        .build()
                    val response = client.send(request, HttpResponse.BodyHandlers.ofString())
                    println(response.body())
                } ?: {
                    println(" fim ")
                }

            }finally {
                semaphore.release(1)
                println("${this.name} liberando acesso !!")
            }
        }
    }



}