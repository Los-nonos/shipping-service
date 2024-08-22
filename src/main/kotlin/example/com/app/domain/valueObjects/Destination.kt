package example.com.app.domain.valueObjects

data class Destination(val type: String, val address: Address?, val branch: Branch?) {
    fun validate() {
        if (address == null && branch == null) {
            throw IllegalStateException("address or branch must be defined in destination")
        }
        address?.validate()
    }

    fun toPrimitives(): Map<String, Any?> {
        return mapOf(
            "type" to this.type,
            "branch" to this.branch?.toPrimitives(),
            "address" to this.address?.toPrimitives(),
        )
    }

    companion object {
        fun fromPrimitives(primitives: Map<String, Any>): Destination{

            return Destination(
                primitives["type"] as String,
                Address.fromPrimitives(primitives["address"] as Map<String, String>?),
                Branch.fromPrimitives(primitives["branch"] as Map<String, String>)
            )
        }
    }
}
