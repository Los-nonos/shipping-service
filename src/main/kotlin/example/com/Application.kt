package example.com

import example.com.app.config.Environment
import example.com.plugins.*

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main(args: Array<String>) {

    Environment.loadDotenv();
    println ("ShippingService is about to start...");
    embeddedServer(Netty, port = 8080) {
        module()
    }.start(wait = true)
    println ("Server is ready and listening on port 8080...");
}

fun Application.module() {
    configureSerialization()
    configureRouting()
}