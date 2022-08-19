package com.jsonb.postgres.PostgresJsonb.controller

import com.jsonb.postgres.PostgresJsonb.assembler.toEntity
import com.jsonb.postgres.PostgresJsonb.controller.model.RequestExample
import com.jsonb.postgres.PostgresJsonb.domain.services.ExampleService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class TestController(
    val exampleService: ExampleService
) {

    @PostMapping
    fun createExample(@RequestBody requestExample: RequestExample): Boolean {
        exampleService.createExample(requestExample.toEntity())
        return true
    }

}


