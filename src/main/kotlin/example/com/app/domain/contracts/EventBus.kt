package example.com.app.domain.contracts

import example.com.app.domain.events.DomainEvent

interface EventBus {
    fun publish(events: List<DomainEvent>)
}