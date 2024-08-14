package example.com.app.infrastructure.http.dto

data class CustomerDTO(
    val name: String,
    val lastName: String,
    val idNumber: String,
    val addressStreet: String,
    val addressNumber: Number,
    val addressCity: String,
    val addressState: String,
    val addressZipCode: String,
    val email: String,
    val phone: String,
)