package example.com.app.infrastructure.persistance.repositories

import com.mongodb.client.MongoCollection
import com.mongodb.client.MongoDatabase
import com.mongodb.client.model.UpdateOptions
import example.com.app.domain.entity.Shipping
import org.bson.Document

class ShippingMongoRepository(private val database: MongoDatabase) {

    private var collection: MongoCollection<Any>;

    init {
        collection = this.database.getCollection("shippings") as MongoCollection<Any>;
    }

    fun save(shipping: Shipping) {
        val options = UpdateOptions().upsert(true);

        val filter = Document("_id", shipping.getId()) // Usa el campo id como filter
        val update = Document("\$set", shipping.toPrimitives())

        collection.updateOne(filter, update, options)
    }


//    fun findOne(id: String): Shipping? {
//        val filter = Document("_id", id);
//
//        val primitives = collection.find(filter).firstOrNull();
//
//        if (primitives == null) {
//            return null;
//        }
//
//        return Shipping.fromPrimitives(primitives as Map<String, String>)
//    }
//
}