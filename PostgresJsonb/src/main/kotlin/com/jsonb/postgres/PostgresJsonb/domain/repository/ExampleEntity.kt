package com.jsonb.postgres.PostgresJsonb.domain.repository

import com.vladmihalcea.hibernate.type.json.JsonBinaryType
import com.vladmihalcea.hibernate.type.json.JsonStringType
import org.hibernate.annotations.Type
import org.hibernate.annotations.TypeDef
import org.hibernate.annotations.TypeDefs
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity(name="example")
@TypeDefs(
    TypeDef(name = "json", typeClass = JsonStringType::class),
    TypeDef(name = "jsonb", typeClass = JsonBinaryType::class)
)
data class ExampleEntity (
    @Id
    val id: String,
    @Type(type = "jsonb")
    @Column(name= "json_data", columnDefinition = "jsonb")
    val jsonData: DataJsonEntity
    )
