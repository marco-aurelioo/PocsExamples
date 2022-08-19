package com.jsonb.postgres.PostgresJsonb.domain.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ExampleRepository : CrudRepository<ExampleEntity,String> {
}