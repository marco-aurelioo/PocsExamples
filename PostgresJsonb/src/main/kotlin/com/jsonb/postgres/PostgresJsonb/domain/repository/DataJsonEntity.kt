package com.jsonb.postgres.PostgresJsonb.domain.repository

import com.fasterxml.jackson.annotation.JsonInclude
import java.io.Serializable

@JsonInclude(JsonInclude.Include.NON_NULL)
data class DataJsonEntity (
    val id: String,
    val data: String ): Serializable
