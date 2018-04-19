package com.abayomi.notepad.view

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import com.abayomi.notepad.R
import com.abayomi.notepad.databinding.ActivityNewNoteBinding
import com.abayomi.notepad.model.Note

class NewNoteActivity : AppCompatActivity(), View.OnClickListener{

    private lateinit var mBinding : ActivityNewNoteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_new_note)

        mBinding.buttonSaveNote.setOnClickListener(this)
        mBinding.imageViewCloseActivity.setOnClickListener(this)

    }

    /**
     * The override onClick method of the View.OnClickListener interface
     */
    override fun onClick(view: View?) {
        when(view!!.id){
            R.id.button_save_note -> attemptToSaveNote()
            R.id.image_view_close_activity -> finish()
        }
    }

    /**
     * This method initially validate the input values.
     * If they are correct values, then the note is being saved to a local storage.
     */
    private fun attemptToSaveNote(){
        val title = mBinding.etTitle.text.toString()
        val details = mBinding.etDetails.text.toString()

        if(TextUtils.isEmpty(title)){
            showToast("Input the note title.")
            return
        }

        if(TextUtils.isEmpty(details)){
            showToast("Input the note details.")
            return
        }

        //create note object and set it's members/fields
        val note = Note()
        note.setTitle(title)
        note.setDetails(details)

        //this save the note to the server at an unspecified time (whenever there is internet connection)
        note.saveEventually()

        /**
         * this saves the object to local Storage so user can manipulate it locally.
         * The pin is the name of the local storage
         */
        note.pin(getString(R.string.key_note_pin))

        showToast("Note saved successfully.")

        finish()

    }


    private fun showToast(message: String){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}
