package example.com.app.infrastructure.http.actions

import example.com.app.application.services.ShippingService
import example.com.app.infrastructure.http.dto.ConfirmShippingDTO
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*

class ConfirmShippingAction{

    suspend fun handleConfirmShipping(call: ApplicationCall, shippingRequest: ConfirmShippingDTO) {
        when (shippingRequest.method) {
            "homeDelivery" -> {
                // Lógica para envío a domicilio
            }
            "branchPickup" -> {
                // Lógica para retiro en sucursal
            }
            "sellerPickup" -> {
                // Lógica para retiro por domicilio del vendedor
            }
            else -> {
                call.respondText("Método de envío no válido", status = HttpStatusCode.BadRequest)
                return
            }
        }

        // Responder con un mensaje de éxito o realizar alguna acción adicional
        call.respondText("Solicitud de envío recibida", status = HttpStatusCode.OK)
    }
}
