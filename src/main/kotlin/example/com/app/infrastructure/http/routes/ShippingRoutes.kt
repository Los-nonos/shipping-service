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
        // Ruta (route) ----> ejecuta una accion en base a un path al que se requiere
        //      Acción (action) ----> valida el body recibido y se lo pasa al handler
        //                  Manejador/Orquestador (Handler) ----> ejecuta la lógica de negocio
        //                                  Repositorio (Repository) ----> se comunica con la base de datos
        //                                                  Entidad (Entity) ----> mapea los datos previos a la consulta a la base de datos                             Base de datos (MongoDB)

        // POST /shippings crea un nuevo envío
        post("/shippings") {
            val body = call.receive<ConfirmShippingCommand>()
            confirmShippingAction.execute(body)

            call.respond(HttpStatusCode.Created, mapOf("message" to "ok"))
        }
    }
}