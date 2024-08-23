package example.com.app.domain.valueObjects

data class Metadata(val key: String, val value: String) {
    fun validate() {
        checkNotNull(key) { "$key must be defined" }
        checkNotNull(value) { "$value must be defined" }
    }
}