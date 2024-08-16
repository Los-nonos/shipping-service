package example.com.app.infrastructure.http.routes

import example.com.app.application.commandHandlers.ConfirmShippingHandler
import example.com.app.application.commands.ConfirmShippingCommand
import example.com.app.infrastructure.http.actions.ConfirmShippingAction
import example.com.app.infrastructure.persistance.config.connectToMongoDB
import example.com.app.infrastructure.persistance.repositories.ShippingMongoRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.shippingRoutes() {
    val mongoDatabase = connectToMongoDB()

    val shippingMongoRepository = ShippingMongoRepository(mongoDatabase)

    val confirmShippingAction = ConfirmShippingAction(ConfirmShippingHandler(shippingMongoRepository))

//    val findUserByIdAction = FindUserByIdAction(FindUserByIdHandler(userMongoUserRepository))

    routing {

        post("/users") {
            val body = call.receive<ConfirmShippingCommand>()
            confirmShippingAction.execute(body);

            call.respond(HttpStatusCode.Created, mapOf("message" to "ok"))
        }
    }
}