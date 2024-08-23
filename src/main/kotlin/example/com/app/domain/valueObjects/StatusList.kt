package example.com.app.domain.valueObjects

data class OrderStatus(val status: String, val substatus: String, val weight: Int)

val orderStatusList: List<OrderStatus> = listOf(
    OrderStatus("created", "pending", 1),
    OrderStatus("created", "in_preparation", 2),
    OrderStatus("created", "ready_to_ship", 3),
    OrderStatus("shipped", "in_transit", 4),
    OrderStatus("shipped", "in_branch", 4),
    OrderStatus("shipped", "last_mile_delivery", 5),
    OrderStatus("shipped", "failed_delivery_attempt", 6),
    OrderStatus("shipped", "invalid_address", 6),
    OrderStatus("shipped", "waiting_for_withdrawal", 6),
    OrderStatus("not_delivered", "invalid_address", 8),
    OrderStatus("not_delivered", "carrier_cancelled", 8),
    OrderStatus("cancelled", "system_cancelled", 9),
    OrderStatus("cancelled", "buyer_cancelled", 9),
    OrderStatus("cancelled", "seller_cancelled", 9),
    OrderStatus("delivered", "delivered", 9)
)