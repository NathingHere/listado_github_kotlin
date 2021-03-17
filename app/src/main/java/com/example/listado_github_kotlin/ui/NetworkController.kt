package com.example.listado_github_kotlin.ui

import com.example.data.Either
import com.example.data.value
import retrofit2.Response
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class NetworkController {


    fun checkException(e: Exception): Either<String, Nothing> {
        return when (e) {
            is UnknownHostException -> error(MessageError.networkDown)
            is SocketTimeoutException -> error(MessageError.timeOut)
            is NullPointerException -> error(MessageError.notFound)
            else -> error(MessageError.errorGeneral)
        }
    }

    fun <T : Any> checkResponse(response: Response<T>): Either<String, T> {

        return when (response.code()) {
            in 200..299 -> if (response.body() != null) {
                value(response.body() as T)
            } else error(response.errorBody().toString())
            else -> error(checkError(response.code()))
        }
    }

    private fun checkError(errorCode: Int): String {

        return when (errorCode) {
            303 -> MessageError.wrongLogin
            401 -> MessageError.unauthorized
            403 -> MessageError.forbidden
            404 -> MessageError.notFound
            405 -> MessageError.methodNotAllowed
            450 -> MessageError.updateApp
            in 500..503 -> MessageError.networkDown
            else -> MessageError.errorGeneral
        }
    }
}