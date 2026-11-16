package edu.pablogj0397.marvelapp.core.cache

import android.content.Context
import edu.pablogj0397.marvelapp.R
import edu.pablogj0397.marvelapp.core.time.AppTime

interface AppCache {
    fun updateTime(key: String)
    fun isExpired (key: String, ttl: Long): Boolean
}
class AppCacheTTL (private val context: Context,
                   private val appTime: AppTime,
                   ): AppCache {

    val sharedPref = context.getSharedPreferences(
        context.getString(R.string.app_cache_TTL), Context.MODE_PRIVATE)


    public override fun updateTime(key: String) {
        val currentTime = System.currentTimeMillis()
        sharedPref.edit().apply {
            putLong(key,appTime.currentTimeToLong())
            apply()
        }
    }

    public override fun isExpired(key: String, ttl: Long): Boolean {
        val savedTime = sharedPref.getLong(key, -1)
        val result = appTime.currentTimeToLong() - savedTime >= ttl
        return result
    }
}