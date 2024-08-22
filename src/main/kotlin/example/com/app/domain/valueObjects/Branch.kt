package example.com.app.domain.valueObjects

data class Branch(val code: String) {
    fun validate() {
        checkNotNull(code) { "Branch must be defined" }
    }

    fun toPrimitives(): Map<String, String> {
        return mapOf(
            "code" to this.code
        )
    }

    companion object {
        fun fromPrimitives(primitives: Map<String, String>?): Branch? {

            if (primitives == null) {
                return null
            }

            return Branch(primitives["code"] as String)
        }
    }
}