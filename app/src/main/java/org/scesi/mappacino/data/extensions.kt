package org.scesi.mappacino.data

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import retrofit2.HttpException
import java.io.IOException
import org.scesi.mappacino.domain.Error

fun Throwable.toError(): Error = when (this) {
    is IOException -> Error.Connectivity
    is HttpException -> Error.Server(code())
    else -> Error.Unknown(message ?: "")
}

suspend fun <T> tryCall(action: suspend () -> T): Either<Error, T> = try {
    action().right()
} catch (e: Exception) {
    e.toError().left()
}

inline fun <reified T> String.toParsedList(): List<T> {
    val typeToken = TypeToken.getParameterized(List::class.java, T::class.java)
    val gson = GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z").create()
    return gson.fromJson<List<T>>(this, typeToken.type) ?: listOf()
}