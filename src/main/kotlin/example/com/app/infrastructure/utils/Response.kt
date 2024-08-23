package example.com.app.infrastructure.utils

import io.ktor.http.*

class Response(
    private val message: String,
    private val code: HttpStatusCode,
    private val success: Boolean,
    private val errors: List<String>? = null,
    private val data: Any? = null
) {
    fun success(data: Any?): Map<String, Any?> {
        val response = Response(this.message, this.code, true, null, data)
        return response.toMap()
    }

    fun error(errors: List<String>): Map<String, Any?> {
        val response = Response(this.message, this.code, false, errors, null)
        return response.toMap()
    }

    private fun toMap(): Map<String, Any?> {
        return mapOf(
            "message" to message,
            "code" to code,
            "success" to success,
            "errors" to errors,
            "data" to data
        )
    }
}