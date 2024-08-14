package example.com.app.infrastructure.persistance.repository

import example.com.app.domain.entity.Shipping
import io.github.cdimascio.dotenv.dotenv
import org.litote.kmongo.coroutine.CoroutineDatabase

class ShippingRepository(
    private val database: CoroutineDatabase,
    private val collectionName: String = dotenv()["SHIPPING_COLLECTION_NAME"]?: "shippings"
) {
    private val shippingCollection = this.database.getCollection<Shipping>(this.collectionName)




}