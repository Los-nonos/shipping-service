package example.com.app.infrastructure.http.routes

import example.com.app.application.commandHandlers.ConfirmShippingHandler
import example.com.app.application.commands.ConfirmShippingCommand
import example.com.app.infrastructure.http.actions.ConfirmShippingAction
import example.com.app.infrastructure.persistance.config.connectToMongoDB
import example.com.app.infrastructure.persistance.repositories.ShippingMongoRepository
import example.com.app.infrastructure.utils.Response
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.shippingRoutes() {
    val mongoDatabase = connectToMongoDB() // Conexión a la base de datos

    val shippingMongoRepository = ShippingMongoRepository(mongoDatabase) // Inyección del repositorio

    val confirmShippingAction =
        ConfirmShippingAction(ConfirmShippingHandler(shippingMongoRepository)) // Inyección del manejador de la acción

//    val findUserByIdAction = FindUserByIdAction(FindUserByIdHandler(userMongoUserRepository))

    routing {
        // Ruta (route) ----> ejecuta una accion en base a un path al que se requiere
        //      Acción (action) ----> valida el body recibido y se lo pasa al handler
        //                  Manejador/Orquestador (Handler) ----> ejecuta la lógica de negocio
        //                                  Repositorio (Repository) ----> se comunica con la base de datos
        //                                                  Entidad (Entity) ----> mapea los datos previos a la consulta a la base de datos

        // POST /shippings crea un nuevo envío
        post("/shippings") {
            println("Received POST request to /shippings")
//            val body = call.receive<ConfirmShippingCommand>()
//            confirmShippingAction.execute(body)
//            val body = call.receive<ConfirmShippingCommand>()
//
//            val validationErrors = body.validate()
//            if (body.validate().isNotEmpty()) {
//                confirmShippingAction.execute(body)
//            } else {
//                call.respond(HttpStatusCode.BadRequest, mapOf("errors" to validationErrors))
//            }
            val response = Response("Hello world", HttpStatusCode.OK, true, null, null)

//            call.respond(HttpStatusCode.Created, response.success(null))
            call.respond(HttpStatusCode.Created, response.success("checking"))
        }
    }
}
