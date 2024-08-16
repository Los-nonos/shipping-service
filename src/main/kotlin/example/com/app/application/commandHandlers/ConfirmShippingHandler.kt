package example.com.app.application.commandHandlers

import example.com.app.application.commands.ConfirmShippingCommand
import example.com.app.domain.entity.Shipping
import example.com.app.infrastructure.persistance.repositories.ShippingMongoRepository
import java.util.*

class ConfirmShippingHandler(
    private val shippingRepository: ShippingMongoRepository // Implement this interface with your preferred data access layer (DAO) implementation.
) {
    fun handle(command: ConfirmShippingCommand) {

        val shipping = Shipping(
            UUID.randomUUID().toString(),
            command.buyerName,
            command.buyerLastName,
            command.buyerAddress,
            command.buyerCity,
            command.buyerCountry,
            command.buyerZipCode,
            command.buyerEmail,
            command.shippingProductId,
            command.sellerCompleteName,
        )

        shippingRepository.save(shipping)
    }


}