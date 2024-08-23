package example.com.app.domain.valueObjects

import kotlinx.serialization.Serializable
import org.jetbrains.annotations.Nullable

@Serializable
data class Piece(
    val product_code: String,
    val reference_number: String? = null
) {
    fun validate() {
        checkNotNull(product_code) { "piece product_code must be defined" }
    }

    fun toPrimitives(): Any? {

        return mapOf(
            "product_code" to this.product_code,
            "reference_number" to this.reference_number,
        )
    }

    companion object {
        fun fromPrimitives(primitives: Map<String, String>): Piece {
            return Piece(
                primitives["product_code"] as String,
                primitives["reference_number"]
            )
        }
    }
}