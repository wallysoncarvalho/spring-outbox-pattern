package com.sadybox.springoutboxpattern

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "cool_entity")
class CoolEntity(
    val name: String
) {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    var id: Long? = null
        private set



}