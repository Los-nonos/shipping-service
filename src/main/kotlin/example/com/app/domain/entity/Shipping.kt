package example.com.app.domain.entity

class Shipping(
    private val id: String,
    private val buyerName: String,
    private val buyerLastName: String,
    private val buyerAddress: String,
    private val buyerCity: String,
    private val buyerCountry: String,
    private val buyerZipCode: Int,
    private val buyerEmail: String,
    private val shippingProductId: String,
    private val sellerCompleteName: String,
)
{

    companion object {
        fun fromPrimitives(primitives: Map<String, String>): Shipping {

            val shipping = Shipping(
                primitives["id"] as String,
                primitives["buyerName"] as String,
                primitives["buyerLastName"] as String,
                primitives["buyerAddress"] as String,
                primitives["buyerCity"] as String,
                primitives["buyerCountry"] as String,
                primitives["buyerZipCode"] as Int,
                primitives["buyerEmail"] as String,
                primitives["shippingProductId"] as String,
                primitives["sellerCompleteName"] as String,
            );

            return shipping;
        }
    }


    fun getId(): String {
        return this.id;
    }

//    fun update(buyerName: String, buyerLastName: String) {
//        this.buyerName = name;
//        this.buyerLastName = lastName;
//        this.buyerAddress = lastName;
//        this.buyerCity = lastName;
//        this.buyerCountry = lastName;
//        this.buyerZipCode = lastName;
//        this.buyerLastName = lastName;
//    }

    fun toPrimitives(): Map<String, String> {
        return mapOf(
            "id" to this.id,
            "buyerName" to this.buyerName,
            "buyerLastName" to this.buyerLastName,
            "buyerAddress" to this.buyerAddress,
            "buyerCity" to this.buyerCity,
            "buyerCountry" to this.buyerCountry,
            "buyerZipCode" to this.buyerZipCode.toString(),
            "buyerEmail" to this.buyerEmail,
            "shippingProductId" to this.shippingProductId,
            "sellerCompleteName" to this.sellerCompleteName
        )
    }
}