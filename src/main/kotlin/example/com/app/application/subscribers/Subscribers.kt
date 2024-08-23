package example.com.app.application.subscribers

import example.com.app.domain.events.ShippingCreated
import example.com.eventBus
import io.ktor.server.application.*


fun Application.subscribers() {
    eventBus.subscribe(ShippingCreated::class.java) { event ->
        if (event is ShippingCreated) {
            println("HOLAAAA, TENGO UN EVENTO DE SHIPPING CREATED")
        }
    }
}