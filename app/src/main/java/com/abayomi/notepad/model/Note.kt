package com.abayomi.notepad.model

import com.parse.ParseClassName
import com.parse.ParseObject
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by dammy_abayomi on 4/10/18
 */

@ParseClassName("Note")
class Note : ParseObject() {

    /**
     * This method sets the note's title
     * put() is an inbuilt method in the ParseObject class
     */
    fun setTitle(title: String) = put("title", title)

    /**
     * This method sets the note's details
     * put() is an inbuilt method in the ParseObject class
     */
    fun setDetails(details: String) = put("details", details)

    /**
     * This method returns the note's title
     * getString() is an inbuilt method in the ParseObject class
     */
    fun getTitle() = getString("title")

    /**
     * This method returns the note's details
     * getString() is an inbuilt method in the ParseObject class
     */
    fun getDetails() = getString("details")

    /**
     * This method returns the formatted text of date the note was updated
     * updatedAt is an inbuilt method in the ParseObject
     */
    fun getDateUpdated() = try {
        getFormattedDateString(updatedAt)
    } catch (e: IllegalStateException) {
        getFormattedDateString(Date())
    }

    /**
     * This method is used to format dates so it can be more presentable in the
     * pattern "MM/dd/yyyy HH:mm"
     */
    private fun getFormattedDateString(date: Date): String {
        val sdf = SimpleDateFormat("MM/dd/yyyy HH:mm")

        return try {
            return sdf.format(date).toString()
        } catch (e: Exception) {
            sdf.format(Date()).toString()
        }
    }

}