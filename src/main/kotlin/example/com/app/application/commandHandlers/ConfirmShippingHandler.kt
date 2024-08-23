package example.com.app.application.commandHandlers

import example.com.app.application.commands.ConfirmShippingCommand
import example.com.app.domain.contracts.EventBus
import example.com.app.domain.contracts.ShippingRepository
import example.com.app.domain.entity.Shipping
import example.com.app.infrastructure.persistance.repositories.ShippingMongoRepository
import java.util.*

class ConfirmShippingHandler(
    private val shippingRepository: ShippingRepository, // Implement this interface with your preferred data access layer (DAO) implementation.
    private val eventBus: EventBus
) {
    fun handle(command: ConfirmShippingCommand) {

        val shipping = Shipping.create(
            command.clientId,
            command.origin,
            command.destination,
            command.returnDestination,
            command.receiver,
            command.sender,
            command.piece,
            command.bu,
            command.shippingMethod,
        )

        shippingRepository.save(shipping)
        eventBus.publish(shipping.pullDomainEvents())
    }


}