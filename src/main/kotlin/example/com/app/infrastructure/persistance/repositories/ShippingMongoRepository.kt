package example.com.app.infrastructure.persistance.repositories

import com.mongodb.client.MongoCollection
import com.mongodb.client.MongoDatabase
import com.mongodb.client.model.UpdateOptions
import example.com.app.domain.contracts.ShippingRepository
import example.com.app.domain.entity.Shipping
import io.github.cdimascio.dotenv.dotenv
import org.bson.Document

val collectionName: String = dotenv()["SHIPPING_COLLECTION_NAME"] ?: "shipping"

class ShippingMongoRepository(private val database: MongoDatabase) : ShippingRepository {

    private var collection: MongoCollection<Any>;

    init {
        collection = this.database.getCollection(collectionName) as MongoCollection<Any>;
    }

    override fun save(shipping: Shipping) {
        println("ShippingMongoRepository - Saving shipping: $shipping")
        val options = UpdateOptions().upsert(true);

        val filter = Document("_id", shipping.getShippingId()) // Usa el campo id como filter
        val update = Document("\$set", shipping.toPrimitives())

        collection.updateOne(filter, update, options)
    }


    override fun findOne(id: String): Shipping? {
        val filter = Document("_id", id);

        val primitives = collection.find(filter).firstOrNull();

        if (primitives == null) {
            return null;
        }

        return Shipping.fromPrimitives(primitives as Map<String, String>)
    }
}