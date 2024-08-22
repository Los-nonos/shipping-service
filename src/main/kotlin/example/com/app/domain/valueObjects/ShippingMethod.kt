package example.com.app.domain.valueObjects

data class ShippingMethod(
    val type: String,
    val courier_code: String,
) {
    fun validate() {

    }

    fun toPrimitives(): Map<String, String> {
        return mapOf(
            "type" to this.type,
            "courier_code" to this.courier_code,
        )
    }

    companion object {
        fun fromPrimitives(primitives: Map<String, String>?): ShippingMethod? {

            if (primitives == null) {
                return null
            }

            return ShippingMethod(
                primitives["type"] as String,
                primitives["courier_code"] as String
            )
        }
    }
}