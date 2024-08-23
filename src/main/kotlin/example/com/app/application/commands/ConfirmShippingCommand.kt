package example.com.app.application.commands

import example.com.app.domain.valueObjects.*
import kotlinx.serialization.Serializable

@Serializable
class ConfirmShippingCommand(
    val clientId: String,
    val origin: Origin? = null,
    val destination: Destination,
    val returnDestination: Destination? = null,
    val receiver: Receiver,
    val sender: Receiver? = null,
    val piece: Piece,
    val bu: String,
    val shippingMethod: ShippingMethod? = null,
) {

    fun validate(): ConfirmShippingCommand {
        checkNotNull(clientId) { throw IllegalArgumentException("ClientId must be defined") }
        origin?.validate()
        checkNotNull(destination) { throw IllegalArgumentException("Destination must be defined") }
        destination.validate()
        returnDestination?.validate()
        checkNotNull(receiver) { throw IllegalArgumentException("Receiver must be defined") }
        receiver.validate()
        sender?.validate()
        checkNotNull(piece) { throw IllegalArgumentException("Piece must be defined") }
        piece.validate()
        checkNotNull(bu) { throw IllegalArgumentException("Business unit must be defined") }
        shippingMethod?.validate()

        return this;
    }
}
