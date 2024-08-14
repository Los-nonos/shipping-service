package example.com.app.config

import org.koin.dsl.module
import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import io.github.cdimascio.dotenv.dotenv
import org.litote.kmongo.coroutine.CoroutineClient
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

// Dotenv casting
val mongoUri = dotenv()["MONGO_URI"]?: "mongodb://localhost:27017"
val mongoDbName = dotenv()["MONGO_DATABASE"]?: "something"

// Mongo Module setup
val mongoModule = module {
    // Db client connection
    single {
        val connectionString = ConnectionString(mongoUri)
        val settings = MongoClientSettings.builder()
            .applyConnectionString(connectionString)
            .build()
        KMongo.createClient(settings).coroutine
    }

    // Db database connection
    single { get<CoroutineClient>().getDatabase(mongoDbName) }
}
