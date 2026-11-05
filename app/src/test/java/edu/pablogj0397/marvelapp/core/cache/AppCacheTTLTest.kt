package edu.pablogj0397.marvelapp.core.cache

import android.content.Context
import android.content.SharedPreferences
import edu.pablogj0397.marvelapp.core.time.AppTime
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class AppCacheTTLTest {

    private val context: Context = mockk()
    private val sharedPref: SharedPreferences = mockk()
    private val editor: SharedPreferences.Editor = mockk(relaxed = true)
    private val appTime: AppTime = mockk()

    private lateinit var appCache: AppCacheTTL


    @Before
    fun setUp(){
        every { context.getString(any())} returns "app_cache"
        every {context.getSharedPreferences("app_cache",Context.MODE_PRIVATE)}
        every { sharedPref.edit()} returns editor
        every { editor.putLong(any(),any())}returns editor

        appCache= AppCacheTTL(context,appTime)
    }

    @Test
    fun `updateTime hace x `() {
    }

    @Test
    fun updateTime() {
    }

    @Test
    fun isExpired() {
    }

}