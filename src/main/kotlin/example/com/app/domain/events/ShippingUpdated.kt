package example.com.app.domain.events

import example.com.app.domain.valueObjects.Status

data class ShippingUpdated(val status: Status, val id: String): DomainEvent {
    override val eventName: String = "shipping.tracking.updated"

    override fun toString(): String {
        return mapOf("status" to status.status, "substatus" to status.substatus, "id" to id).toString()
    }
}
