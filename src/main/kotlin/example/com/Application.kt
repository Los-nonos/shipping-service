package example.com

import com.fasterxml.jackson.databind.SerializationFeature

import example.com.app.application.subscribers.subscribers
import example.com.app.infrastructure.eventBus.InMemoryEventBus
import example.com.app.infrastructure.http.routes.healthRoutes
import example.com.app.infrastructure.http.routes.shippingRoutes
import example.com.app.infrastructure.persistance.config.connectToMongoDB
import io.ktor.http.*
import io.ktor.serialization.jackson.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.statuspages.StatusPages
import io.ktor.server.request.*
import io.ktor.server.response.*
import org.slf4j.LoggerFactory

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.errorHandler() {
    install(StatusPages) {
        exception<Throwable> { call, cause ->
            logError(call, cause)

            if (cause is NotFoundException) {
                call.respond(HttpStatusCode.NotFound, mapOf("error" to cause.message))
            } else {
                call.respond(HttpStatusCode.InternalServerError, mapOf("error" to "Internal server error"))
            }
        }
    }
}

val eventBus = InMemoryEventBus()

fun Application.module() {
    install(ContentNegotiation) {
        json()
        jackson {
            enable(SerializationFeature.INDENT_OUTPUT)
        }
    }
    try{
        connectToMongoDB()
        healthRoutes()
        shippingRoutes()
        subscribers()
        errorHandler()
    } catch (e: Exception) {
        println("Error configuring application: ${e.message}")
    }
}



fun logError(call: ApplicationCall, cause: Throwable) {
    val log = LoggerFactory.getLogger("ErrorLogger")
    val requestUri = call.request.uri
    log.error("Error at $requestUri: ${cause.localizedMessage}", cause)
}