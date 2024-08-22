package example.com.app.domain.events

import example.com.app.domain.valueObjects.*

data class ShippingCreated(
    val id: String,
    val clientId: String,
    val origin: Origin?,
    val destination: Destination,
    val returnDestination: Destination?,
    val receiver: Receiver,
    val sender: Receiver?,
    val piece: Piece,
    val bu: String,
    val shippingMethod: ShippingMethod?
) : DomainEvent {
    override val eventName: String = "shipping.created"

    override fun toString(): String {
        return mapOf(
            "id" to id,
            "clientId" to this.clientId,
            "origin" to this.origin?.toPrimitives(),
            "destination" to this.destination.toPrimitives(),
            "returnDestination" to this.returnDestination?.toPrimitives(),
            "receiver" to this.receiver.toPrimitives(),
            "sender" to this.sender?.toPrimitives(),
            "piece" to this.piece.toPrimitives(),
            "bu" to this.bu,
            "shippingMethod" to this.shippingMethod?.toPrimitives()
        ).toString()
    }
}