package com.abayomi.notepad.view

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.abayomi.notepad.R
import com.abayomi.notepad.databinding.ActivityMainBinding
import com.abayomi.notepad.model.Note
import com.parse.FindCallback
import com.parse.ParseQuery

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setSupportActionBar(toolbar)

        mBinding.fab.setOnClickListener { view ->
            startActivity(Intent(this, NewNoteActivity::class.java))
        }

    }

    public override fun onResume() {
        super.onResume()

        fetchNotesFromLocalDataStore()
    }

    /**
     * This method fetches note(s) from a local storage (NOT the remote server)
     */
    private fun fetchNotesFromLocalDataStore(){
        val query = ParseQuery.getQuery(Note::class.java)
        query.orderByDescending("updatedAt")
        query.fromPin(getString(R.string.key_note_pin)) //this pin specifies that the query should be executed in a local storage

        query.findInBackground(FindCallback<Note> { noteList, e ->
            if(e == null){
                if(noteList.count() > 0){
                    //the query successfully retrieved so note, hence, create a recycler view
                    setUpRecyclerView(noteList)
                }else{
                    //show a text view displaying that there are no notes
                    mBinding.textViewNoNote.visibility = View.VISIBLE
                }
            }else{
                showToast(e.message.toString())
            }
        })
    }

    /**
     * This method sets up the recycler view using the NoteAdapter class
     */
    private fun setUpRecyclerView(noteList: List<Note>) {
        val layoutManager = LinearLayoutManager(this)
        mBinding.recyclerView.layoutManager = layoutManager
        mBinding.recyclerView.itemAnimator = DefaultItemAnimator()
        mBinding.recyclerView.adapter = NoteAdapter(noteList)
        mBinding.recyclerView.isNestedScrollingEnabled = false
    }

    private fun showToast(message: String){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }


}
