package example.com.app.domain.valueObjects

data class Phone(val type: String?, val number: String, val area_code: String?) {
    fun validate() {
        checkNotNull(number) { "Number was not specified" }
        check(number.isNotEmpty()) { "Number length is incorrect" }
        check(number.length <= 15) { "Number must be less than 15 characters" }
    }

    fun toPrimitives(): Map<String, String?> {
        return mapOf(
            "type" to this.type,
            "area_code" to this.area_code,
            "number" to this.number,
        )
    }

    companion object {
        fun fromPrimitives(primitives: Map<String, String>): Phone {
            return Phone(
                primitives["type"],
                primitives["number"] as String,
                primitives["area_code"]
            )
        }
    }
}
