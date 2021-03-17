package com.example.data

sealed class Either<out E, out V> {
    data class Error<out E>(val error: E) : Either<E, Nothing>()
    data class Value<out V>(val value: V) : Either<Nothing, V>()
}

fun <V> either(data: () -> V): Either<Exception, V> =
        try {
            value(data())
        } catch (e: Exception) {
            error(e)
        }

fun <V> value(value: V): Either<Nothing, V> = Either.Value(value)

fun <E> error(error: E): Either<E, Nothing> = Either.Error(error)

inline infix fun <E, E2, V> Either<E, V>.getError(f: (E) -> E2): Either<E2, V> = when (this) {
    is Either.Error -> Either.Error(f(error))
    is Either.Value -> this
}

inline infix fun <E, V, V2> Either<E, V>.getValue(v: (V) -> V2): Either<E, V2> = when (this) {
    is Either.Value -> Either.Value(v(value))
    is Either.Error -> this
}

inline fun <E, V, A> Either<E, V>.fold(e: (E) -> A, v: (V) -> A): A = when (this) {
    is Either.Error -> e(error)
    is Either.Value -> v(value)
}

