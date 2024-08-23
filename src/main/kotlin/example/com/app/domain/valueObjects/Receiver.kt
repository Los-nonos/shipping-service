package example.com.app.domain.valueObjects

import kotlinx.serialization.Serializable

@Serializable
data class Receiver(
    val name: String,
    val email: String,
    val documentType: String,
    val documentNumber: String,
    val phone: Phone
) {
    fun validate() {
        checkNotNull(name) { "Complete Name was not specified" }
        check(name.isNotEmpty()) { "Complete Name length is incorrect"}
        check(name.length <= 45) { "Complete Name must be less than 45 characters"}
        checkNotNull(email) { "eMail was not specified" }
        check(email.isNotEmpty()) { "eMail length is incorrect"}
        check(email.length <= 55) { "eMail must be less than 55 characters"}
        checkNotNull(documentType) { "Document type was not specified" }
        check(documentType.isNotEmpty()) { "Document type length is incorrect" }
        checkNotNull(documentNumber) { "Document number was not specified" }
        check(documentNumber.isNotEmpty()) { "Document number length is incorrect"}
        check(documentNumber.length <= 20) { "Document number must be less than 20 characters"}
        phone.validate()
    }

    fun toPrimitives(): Map<String, Any> {
        return mapOf(
            "name" to this.name,
            "phone" to this.phone.toPrimitives(),
            "documentType" to this.documentType,
            "documentNumber" to this.documentNumber,
            "email" to this.email
        )
    }

    companion object {
        fun fromPrimitives(map: Map<String, Any>): Receiver {
            return Receiver(
                map["name"] as String,
                map["email"] as String,
                map["documentType"] as String,
                map["documentNumber"] as String,
                Phone.fromPrimitives(map["phone"] as Map<String, String>)
            )
        }
    }
}
