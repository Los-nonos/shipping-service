package example.com.app.application.commands

class ConfirmShippingCommand(
    val buyerName: String,
    val buyerLastName: String,
    val buyerAddress: String,
    val buyerCity: String,
    val buyerCountry: String,
    val buyerZipCode: Int,
    val buyerEmail: String,
    val shippingProductId: String,
    val sellerCompleteName: String,
) {
    fun validate(): ConfirmShippingCommand {
        checkNotNull(buyerName) { throw IllegalArgumentException("BuyerName must be defined") }
        checkNotNull(buyerLastName) { throw IllegalArgumentException("BuyerLastName must be defined") }
        checkNotNull(buyerAddress) { throw IllegalArgumentException("BuyerAddress must be defined") }
        checkNotNull(buyerCity) { throw IllegalArgumentException("BuyerCity must be defined") }
        checkNotNull(buyerCountry) { throw IllegalArgumentException("BuyerCountry must be defined") }
        checkNotNull(buyerZipCode) { throw IllegalArgumentException("BuyerZipCode must be defined") }
        checkNotNull(buyerEmail) { throw IllegalArgumentException("BuyerEmail must be defined") }
        checkNotNull(shippingProductId) { throw IllegalArgumentException("ShippingProductId must be defined") }
        checkNotNull(sellerCompleteName) { throw IllegalArgumentException("SellerCompleteName must be defined") }

        return this;
    }
}
