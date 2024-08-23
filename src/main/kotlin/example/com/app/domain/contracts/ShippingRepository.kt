package example.com.app.domain.contracts

import example.com.app.domain.entity.Shipping

interface ShippingRepository {
    fun save(shipping: Shipping)
    fun findOne(id: String): Shipping?
}