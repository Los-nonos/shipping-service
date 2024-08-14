package example.com

import example.com.app.config.mongoModule
import example.com.app.infrastructure.http.routes.shippingRoutes
import example.com.plugins.*
import io.github.cdimascio.dotenv.dotenv

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.routing.*
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger

// Dotenv configuration
val apiPort = dotenv()["API_PORT"]?.toInt()?: 8080
val apiHost = dotenv()["API_HOST"]?: "localhost"


fun main(args: Array<String>) {
    println ("ShippingService is about to start...");
    embeddedServer(Netty, apiPort, apiHost) { // Server configuration
        module() // Application module configuration
        install(Koin) { // Koin configuration
            slf4jLogger() // Logger for Koin
            modules(mongoModule) // Mongo module injector
        }

        routing {
            shippingRoutes()  // Shipping routes register
        }
    }.start(wait = true) // Start the server
    println ("Server is ready and listening on port ${apiHost}:${apiPort}...");
}

fun Application.module() {
    configureSerialization()  // Configure serialization
}