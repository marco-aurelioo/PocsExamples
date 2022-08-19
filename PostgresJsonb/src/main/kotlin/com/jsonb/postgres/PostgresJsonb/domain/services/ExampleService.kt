package com.jsonb.postgres.PostgresJsonb.domain.services

import com.jsonb.postgres.PostgresJsonb.domain.repository.ExampleEntity
import com.jsonb.postgres.PostgresJsonb.domain.repository.ExampleRepository
import org.springframework.stereotype.Service

@Service
class ExampleService(
    val exampleRepository: ExampleRepository
) {


    fun createExample(exampleEntity: ExampleEntity){

        val teste = exampleRepository.findAll()
        teste.forEach(){
            print(it.jsonData)
        }
        exampleRepository.save(exampleEntity)
    }

}