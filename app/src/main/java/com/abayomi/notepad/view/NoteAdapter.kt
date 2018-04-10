package com.abayomi.notepad.view

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.abayomi.notepad.R
import com.abayomi.notepad.databinding.AdapterNoteBinding
import com.abayomi.notepad.model.Note


/**
 * Created by dammy_abayomi on 4/10/18
 */
class NoteAdapter(private val noteList: List<Note>) : RecyclerView.Adapter<NoteAdapter.NoteBindingHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteBindingHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_note, parent, false)

        return NoteBindingHolder(view)
    }

    override fun onBindViewHolder(holder: NoteBindingHolder, position: Int) {
        val note = noteList[position]

        holder.binding.textViewTitle.text = note.getTitle()
        holder.binding.textViewDetails.text = note.getDetails()
        holder.binding.textViewDate.text = note.getDateUpdated()

    }

    override fun getItemCount(): Int {
        return noteList.size
    }


    class NoteBindingHolder(rowView: View) : RecyclerView.ViewHolder(rowView) {

        val binding: AdapterNoteBinding

        init {
            binding = DataBindingUtil.bind(rowView)
        }


    }

}
