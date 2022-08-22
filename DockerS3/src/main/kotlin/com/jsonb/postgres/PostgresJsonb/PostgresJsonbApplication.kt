package com.jsonb.postgres.PostgresJsonb

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class PostgresJsonbApplication

fun main(args: Array<String>) {
	runApplication<PostgresJsonbApplication>(*args)
}
