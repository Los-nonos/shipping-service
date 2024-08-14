package example.com.app.infrastructure.http.routes

import example.com.app.infrastructure.http.actions.ConfirmShippingAction
import example.com.app.infrastructure.http.dto.ConfirmShippingDTO
import io.ktor.http.*
import io.ktor.server.routing.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*
import org.koin.ktor.ext.inject

fun Route.shippingRoutes() {
    val confirmShippingAction: ConfirmShippingAction by inject()

    // Confirm shipping endpoint
    post("/shipping/confirm") {
        val shipping = call.receive<ConfirmShippingDTO>() // Receive the request body as a ConfirmShippingDTO object
        val result = confirmShippingAction.handleConfirmShipping(call, shipping) // Call the action to confirm the shipping passing the requested DTO
        call.respond(HttpStatusCode.Created, result) // Responds to user with the result
    }

//    get("/shipping/{id}") {
//        val id = call.parameters["id"] ?: return@get call.respondText(
//            "Falta el ID del envío",
//            status = HttpStatusCode.BadRequest
//        )
//        val shipping = shippingController.getShippingById(id)
//        if (shipping != null) {
//            call.respond(shipping)
//        } else {
//            call.respondText("Envío no encontrado", status = HttpStatusCode.NotFound)
//        }
//    }
}
