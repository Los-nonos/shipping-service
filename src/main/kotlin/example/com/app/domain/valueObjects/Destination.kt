package example.com.app.domain.valueObjects

import kotlinx.serialization.Serializable

@Serializable
data class Destination(val type: String, val address: Address?) {
    fun validate() {
        if (address == null) {
            throw IllegalStateException("address or branch must be defined in destination")
        }
        address?.validate()
    }

    fun toPrimitives(): Map<String, Any?> {
        return mapOf(
            "type" to this.type,
            "address" to this.address?.toPrimitives(),
        )
    }

    companion object {
        fun fromPrimitives(primitives: Map<String, Any>): Destination{

            return Destination(
                primitives["type"] as String,
                Address.fromPrimitives(primitives["address"] as Map<String, String>?),
            )
        }
    }
}
