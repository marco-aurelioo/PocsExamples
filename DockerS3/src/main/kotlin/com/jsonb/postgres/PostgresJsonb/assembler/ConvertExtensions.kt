package com.jsonb.postgres.PostgresJsonb.assembler

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.jsonb.postgres.PostgresJsonb.controller.model.RequestExample
import com.jsonb.postgres.PostgresJsonb.domain.repository.DataJsonEntity
import com.jsonb.postgres.PostgresJsonb.domain.repository.ExampleEntity

class ConvertExtensions {

}

fun RequestExample.toEntity(): ExampleEntity {
    return ExampleEntity(
        id = this.id,
        jsonData = jacksonObjectMapper().readValue(this.json,DataJsonEntity::class.java))
}