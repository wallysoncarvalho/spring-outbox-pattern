package com.sandbox.springoutboxpattern

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.annotations.NaturalId
import org.springframework.data.domain.AbstractAggregateRoot

@Entity
@Table(name = "cool_entity")
class CoolEntity(
    val name: String
) {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
        private set

}
