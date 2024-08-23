package example.com.app.domain.events

data class ShippingHasNeedPrepared(val id: String): DomainEvent {
    override val eventName: String = "shipping.needs.prepared"

    override fun toString(): String {
        return mapOf(
            "id" to this.id
        ).toString()
    }
}
