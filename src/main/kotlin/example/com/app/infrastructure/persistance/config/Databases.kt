package example.com.app.infrastructure.persistance.config

import com.mongodb.client.*
import io.github.cdimascio.dotenv.dotenv
import io.ktor.server.application.*
import io.ktor.server.config.*

/**
 * Establishes connection with a MongoDB database.
 *
 * The following configuration properties (in application.yaml/application.conf) can be specified:
 * * `db.mongo.user` username for your database
 * * `db.mongo.password` password for the user
 * * `db.mongo.host` host that will be used for the database connection
 * * `db.mongo.port` port that will be used for the database connection
 * * `db.mongo.maxPoolSize` maximum number of connections to a MongoDB server
 * * `db.mongo.database.name` name of the database
 *
 * IMPORTANT NOTE: in order to make MongoDB connection working, you have to start a MongoDB server first.
 * See the instructions here: https://www.mongodb.com/docs/manual/administration/install-community/
 * all the paramaters above
 *
 * @returns [MongoDatabase] instance
 * */
fun Application.connectToMongoDB(): MongoDatabase {
        val user = dotenv()["MONGO_INITDB_ROOT_USERNAME"] ?: "root"
        val password = dotenv()["MONGO_INITDB_ROOT_PASSWORD"] ?: "example"
        val host = dotenv()["MONGO_DB_HOST"] ?: "localhost"
        val port = dotenv()["MONGO_DB_PORT"]?.toInt() ?: 27017
        val maxPoolSize = dotenv()["MONGO_DB_MAX_POOL_SIZE"]?.toInt() ?: 20
        val databaseName = dotenv()["MONGO_DATABASE_NAME"] ?: "shippings"

        val credentials = user.let { userVal -> password.let { passwordVal -> "$userVal:$passwordVal@" } }.orEmpty()

        println("credentials: $credentials")

        val uri = "mongodb://$credentials$host:$port/?maxPoolSize=$maxPoolSize&w=majority"

        val mongoClient = MongoClients.create(uri)
        val database = mongoClient.getDatabase(databaseName)

        environment.monitor.subscribe(ApplicationStopped) {
            mongoClient.close()
        }

        return database
}
