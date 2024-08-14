package example.com.app.application.services

import example.com.app.infrastructure.http.dto.ConfirmShippingDTO

class ShippingService {


    suspend fun confirmShipping(confirmShippingDto: ConfirmShippingDTO): String {
        shippingCollection.insertOne(shipping)
        return "Envío confirmado"
    }
}