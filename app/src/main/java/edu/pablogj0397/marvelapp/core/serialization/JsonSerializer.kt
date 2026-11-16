package edu.pablogj0397.marvelapp.core.serialization

interface JsonSerializer<T> {
    fun toJson(obj: T): String
    fun fromJson(json: String): T
}
