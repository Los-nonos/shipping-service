package example.com

import example.com.app.config.mongoModule
import example.com.plugins.*
import io.github.cdimascio.dotenv.dotenv

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger

fun main(args: Array<String>) {
    val dotenv = dotenv()
    val apiPort = dotenv["API_PORT"]?.toInt()?: 8080
    val apiHost = dotenv["API_HOST"]?: "localhost"

    println ("ShippingService is about to start...");
    embeddedServer(Netty, apiPort, apiHost) {
        module()
        install(Koin) {
            slf4jLogger()
            modules(mongoModule)
        }
    }.start(wait = true)
    println ("Server is ready and listening on port ${apiHost}:${apiPort}...");
}

fun Application.module() {
    configureSerialization()
    configureRouting()
}