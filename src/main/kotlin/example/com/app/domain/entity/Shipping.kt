package example.com.app.domain.entity

import example.com.app.domain.events.DomainEvent
import example.com.app.domain.events.ShippingCreated
import example.com.app.domain.events.ShippingHasNeedPrepared
import example.com.app.domain.events.ShippingReadyToDeliver
import example.com.app.domain.valueObjects.*
import java.util.UUID

data class Shipping(
    val id: String,
    val clientId: String,
    var origin: Origin?,
    val destination: Destination,
    val returnDestination: Destination?,
    val receiver: Receiver,
    var sender: Receiver?,
    val piece: Piece,
    val bu: String,
    var shippingMethod: ShippingMethod?
) {

    private var events: List<DomainEvent> = mutableListOf();
    private var status: Status? = null

    companion object {
        fun fromPrimitives(primitives: Map<String, Any>): Shipping {

            val shipping = Shipping(
                primitives["id"] as String,
                primitives["clientId"] as String,
                Origin.fromPrimitives(primitives["origin"] as Map<String, Any>),
                Destination.fromPrimitives(primitives["destination"] as Map<String, Any>),
                Destination.fromPrimitives(primitives["returnDestination"] as Map<String, Any>),
                Receiver.fromPrimitives(primitives["receiver"] as Map<String, Any>),
                primitives["sender"]?.let { Receiver.fromPrimitives(primitives["sender"] as Map<String, Any>) },
                Piece.fromPrimitives(primitives["piece"] as Map<String, String>),
                primitives["bu"] as String,
                ShippingMethod.fromPrimitives(primitives["shippingMethod"] as Map<String, String>?)
            );

            shipping.status = Status.fromPrimitives(primitives["status"] as Map<String, String>?)

            return shipping;
        }

        fun create(
            clientId: String,
            origin: Origin?,
            destination: Destination,
            returnDestination: Destination?,
            receiver: Receiver,
            sender: Receiver?,
            piece: Piece,
            bu: String,
            shippingMethod: ShippingMethod?
        ): Shipping {

            val shipping = Shipping(
                UUID.randomUUID().toString(),
                clientId,
                origin,
                destination,
                returnDestination,
                receiver,
                sender,
                piece,
                bu,
                shippingMethod
            )

            shipping.recordEvent(
                ShippingCreated(
                    shipping.id,
                    clientId,
                    origin,
                    destination,
                    returnDestination,
                    receiver,
                    sender,
                    piece,
                    bu,
                    shippingMethod
                )
            )

            shipping.status = Status("created", "pending")

            if (origin != null && shippingMethod != null) {
                shipping.status = Status("created", "in_preparation")

                shipping.recordEvent(
                    ShippingReadyToDeliver(
                        shipping.id
                    )
                )
            } else {
                shipping.recordEvent(
                    ShippingHasNeedPrepared(
                        shipping.id
                    )
                )
            }

            return shipping
        }
    }

    private fun recordEvent(event: DomainEvent) {
        this.events = this.events.plus(event)
    }


    fun toPrimitives(): Map<String, Any?> {
        return mapOf(
            "id" to this.id,
            "clientId" to this.clientId,
            "origin" to this.origin?.toPrimitives(),
            "destination" to this.destination.toPrimitives(),
            "receiver" to this.receiver.toPrimitives(),
            "sender" to this.sender?.toPrimitives(),
            "piece" to this.piece.toPrimitives(),
            "bu" to this.bu,
            "shippingMethod" to this.shippingMethod?.toPrimitives()
        )
    }

    fun getShippingId(): String {
        return this.id;
    }

    fun pullDomainEvents(): List<DomainEvent> {
        return this.events;
    }

//    fun update(buyerName: String, buyerLastName: String) {
//        this.buyerName = name;
//        this.buyerLastName = lastName;
//        this.buyerAddress = lastName;
//        this.buyerCity = lastName;
//        this.buyerCountry = lastName;
//        this.buyerZipCode = lastName;
//        this.buyerLastName = lastName;
//    }

}