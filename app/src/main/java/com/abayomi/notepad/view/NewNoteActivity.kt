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
import java.util.*

class NewNoteActivity : AppCompatActivity(), View.OnClickListener{

    private lateinit var mBinding : ActivityNewNoteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_new_note)

        mBinding.buttonSaveNote.setOnClickListener(this)
        mBinding.imageViewCloseActivity.setOnClickListener(this)

    }

    override fun onClick(view: View?) {
        when(view!!.id){
            R.id.button_save_note -> attemptToSaveNote()
            R.id.image_view_close_activity -> finish()
        }
    }

    /**
     * Validate the input values and save the contents
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

        //create note object and set it's members
        val note = Note()
        note.setTitle(title)
        note.setDetails(details)

        note.saveEventually() //attempt save contents to server when their is internet connection
        note.pin(getString(R.string.key_note_pin)) //pin object to localDataStore so user can manipulate it locally

        showToast("Note saved successfully.")

        finish()

    }


    private fun showToast(message: String){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}
