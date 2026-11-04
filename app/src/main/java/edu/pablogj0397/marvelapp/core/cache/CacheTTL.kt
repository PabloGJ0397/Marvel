package edu.pablogj0397.marvelapp.core.cache

import android.content.Context
import edu.iesam.marvelapp.R

interface AppCache {
    fun saveCurrentTime(key: String)
    fun isExpired (key: String, ttl: Long): Boolean
}
class CacheTTL (private val context: Context,
                private val cacheKey: String,
                private val cacheLifeTime: Long) {

    val sharedPref = context.getSharedPreferences(
        context.getString(R.string.app_cache_TTL), Context.MODE_PRIVATE)


    public fun saveCurrentTime() {
        val currentTime = System.currentTimeMillis()
        sharedPref.edit().apply {
            putLong(cacheKey,currentTime)
            apply()
        }
    }

    public fun isExpired(): Boolean {
        val savedTime = sharedPref.getLong(cacheKey, -1)
        val currentTime = System.currentTimeMillis()
        val result = currentTime - savedTime >= cacheLifeTime
        return result
    }
}