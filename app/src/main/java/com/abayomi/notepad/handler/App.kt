package com.abayomi.notepad.handler

import android.app.Application
import com.abayomi.notepad.model.Note
import com.parse.Parse
import com.parse.ParseObject

/**
 * Created by dammy_abayomi on 4/10/18
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()

        initParseServer()
    }

    private fun initParseServer() {
        Parse.enableLocalDatastore(applicationContext)
        ParseObject.registerSubclass(Note::class.java)

        Parse.initialize(Parse.Configuration.Builder(applicationContext)
                .applicationId("APP_ID")
                .clientKey("CLIENT_KEY")
                .server("https://parseapi.back4app.com")
                .enableLocalDataStore()
                .build()
        )

    }

}