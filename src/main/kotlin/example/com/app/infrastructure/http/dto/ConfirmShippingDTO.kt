package example.com.app.infrastructure.http.dto

import java.util.UUID

data class ConfirmShippingDTO(
    val sellerUUID: UUID,
    val method: String,
    val packageToSend: PackageDTO,
    val buyer: CustomerDTO,
)