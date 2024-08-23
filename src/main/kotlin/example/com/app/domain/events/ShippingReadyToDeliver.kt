package example.com.app.domain.events

data class ShippingReadyToDeliver(val id: String): DomainEvent {
    override val eventName: String = "shipping.ready"

    override fun toString(): String {
        return mapOf(
            "id" to this.id
        ).toString()
    }
}
