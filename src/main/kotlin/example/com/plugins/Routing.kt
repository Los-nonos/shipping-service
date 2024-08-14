package example.com.plugins

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import org.litote.kmongo.coroutine.CoroutineDatabase

fun Application.configureRouting() {
    routing {
        val database: CoroutineDatabase by inject()

        get("/") {

        }

        get("/") {
            call.respondText("Hello World!")
        }
    }
}
