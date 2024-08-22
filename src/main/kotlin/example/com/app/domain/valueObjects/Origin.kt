package example.com.app.domain.valueObjects


data class Origin(val type: String?, val address: Address?, val branch: Branch?) {
    fun validate() {
        //TODO validate type
        if (address == null && branch == null) {
            throw IllegalStateException("address or branch must be defined in origin")
        }

        address?.validate()
        branch?.validate()
    }

    fun toPrimitives(): Map<String, Any?> {
        return mapOf(
            "type" to this.type,
            "branch" to this.branch?.let { it.toPrimitives() },
            "address" to this.address?.let { it.toPrimitives()}
        )
    }

    companion object {
        fun fromPrimitives(s: Map<String, Any>?): Origin? {

            if (s == null) {
                return null
            }

            return Origin(
                s["type"] as String,
                Address.fromPrimitives(s["address"] as Map<String, String>?),
                Branch.fromPrimitives(s["branch"] as Map<String, String>?)
            )
        }
    }
}
