package com.sandbox.springoutboxpattern

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.Instant

@Entity
@Table(name = "outbox")
class Outbox(
    @Column(name = "type")
    val type: String,
    @Column(name = "content")
    val content: String,
    @Column(name = "scheduled_to")
    val scheduledTo: Instant
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
        private set

    @Column(name = "created_at")
    private var createdAt: Instant = Instant.now()


    @Column(name = "processed_at")
    private var processedAt: Instant? = null

    fun finish() { processedAt = Instant.now() }
    override fun toString(): String {
        return "Outbox(type='$type', content='$content', scheduledTo=$scheduledTo, id=$id, createdAt=$createdAt, processedAt=$processedAt)"
    }
}
