package example.com.app.domain.valueObjects

data class Address(
    val city: String,
    val region: String?,
    val country: String?,
    val zip_code: String,
    val street: String,
    val street_number: String,
    val floor: String?,
    val dept: String?,
    var state: String
) {
    fun validate() {
        if (state == "Ciudad Autónoma de Buenos Aires") state = "CABA"

        checkNotNull(city) { "City name was not specified" }
        check(city.isNotEmpty()) { "City length is incorrect" }
        check(city.length <= 45) { "City must be less than 45 characters" }
        checkNotNull(zip_code) { "Zip code was not specified" }
        check(zip_code.isNotEmpty()) { "Zip Code length is incorrect" }
        check(zip_code.length <= 8) { "Zip Code must be less than 9 characters" }
        checkNotNull(street) { "Street was not specified" }
        check(street.isNotEmpty()) { "Street length is incorrect" }
        check(street.length <= 40) { "Street must be less than 40 characters" }
        checkNotNull(street_number) { "Number was not specified" }
        check(street_number.isNotEmpty()) { "Number length is incorrect" }
        check(street_number.length <= 40) { "Number must be less than 40 characters" }
        checkNotNull(state) { "State name was not specified" }
        check(state.isNotEmpty()) { "State length is incorrect" }
        check(state.length <= 25) { "State must be less than 25 characters" }

    }

    fun toPrimitives(): Map<String, String?> {
        return mapOf(
            "city" to this.city,
            "region" to this.region,
            "country" to this.country,
            "zip_code" to this.zip_code,
            "street" to this.street,
            "street_number" to this.street_number,
            "floor" to this.floor,
            "dept" to this.dept,
            "state" to this.state
        )
    }

    companion object {
        fun fromPrimitives(primitives: Map<String, String?>?): Address? {

            if (primitives == null) {
                return null
            }

            return Address(
                primitives["city"] as String,
                primitives["region"],
                primitives["country"],
                primitives["zip_code"] as String,
                primitives["street"] as String,
                primitives["street_number"] as String,
                primitives["floor"],
                primitives["dept"],
                primitives["state"] as String
            )
        }
    }
}