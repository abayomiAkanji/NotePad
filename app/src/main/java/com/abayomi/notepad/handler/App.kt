package com.abayomi.notepad.handler

import android.app.Application
import com.abayomi.notepad.R
import com.abayomi.notepad.model.Note
import com.parse.FindCallback
import com.parse.Parse
import com.parse.ParseObject
import com.parse.ParseQuery

/**
 * Created by dammy_abayomi on 4/10/18
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()

        initParseLibrary()

        fetchNoteFromServer()
    }

    /**
     * This method initializes the parse library that was added to the gradle file.
     * This makes the parse library usable in this project (in any classes)
     */
    private fun initParseLibrary() {
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

    /**
     * This method attempts to fetch note from the remote server.
     * If note exists, it pinned it to the localDataStore so device
     * can use it at any point in time
     */
    private fun fetchNoteFromServer() {
        val query = ParseQuery.getQuery(Note::class.java)
        query.orderByDescending("updatedAt")
        query.findInBackground(FindCallback<Note> { noteList, e ->
            if (e == null) {
                if (noteList.count() > 0) {
                    pinNotesToLocalDataStore(noteList)
                }
            } else {
                e.printStackTrace()
            }
        })
    }

    /**
     * This method pins the NoteList to the localDataStore
     */
    private fun pinNotesToLocalDataStore(list: List<Note>) {
        val noteKey = getString(R.string.key_note_pin)

        //first remove the old notes
        ParseObject.unpinAllInBackground(noteKey) { e ->
            if (e == null) {
                //save the new notes afresh
                ParseObject.pinAllInBackground(noteKey, list)
            }
        }
    }

}