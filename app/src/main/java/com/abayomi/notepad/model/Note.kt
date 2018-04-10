package com.abayomi.notepad.model

import com.parse.ParseClassName
import com.parse.ParseObject
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by dammy_abayomi on 4/10/18
 */

@ParseClassName("Note")
class Note : ParseObject(){

    fun setTitle(title : String) = put("title", title)

    fun setDetails(details : String) = put("details", details)

    fun getTitle() = getString("title")

    fun getDetails() = getString("details")

    fun getDateCreated() = try{getFormattedDateString(createdAt)}catch (e : IllegalStateException){getFormattedDateString(Date())}

    fun getDateUpdated() = try{getFormattedDateString(updatedAt)}catch (e : IllegalStateException){getFormattedDateString(Date())}

    private fun getFormattedDateString(date: Date): String {
        val sdf = SimpleDateFormat("MM/dd/yyyy HH:mm")

        return try {
            return sdf.format(date).toString()
        }catch (e : Exception){
            sdf.format(Date()).toString()
        }
    }

}