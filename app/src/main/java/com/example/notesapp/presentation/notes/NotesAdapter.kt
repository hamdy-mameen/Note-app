package com.example.notesapp.presentation.notes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.domain.Note
import com.example.notesapp.databinding.NotesItemBinding
import java.time.Instant
import java.time.Year
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class NotesAdapter @Inject constructor():RecyclerView.Adapter<NotesAdapter.NoteHolder>() {
    var listener:OnNoteClicked?=null
    inner class NoteHolder( val binding:NotesItemBinding):ViewHolder(binding.root)
    private val diffCallBack = object:DiffUtil.ItemCallback<Note>(){
        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem == newItem
        }

    }
     val differ = AsyncListDiffer(this,diffCallBack)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteHolder {
        val binding = NotesItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return NoteHolder(binding)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: NoteHolder, position: Int) {
        val note = differ.currentList[position]
        holder.binding.apply {
            noteTitle.text = note.title
            noteDescription.text = note.description
                val localDate = Instant.ofEpochMilli(note.date)
                .atZone(ZoneId.systemDefault())
                .toLocalDate()
                val year = localDate.year
                val current = Year.now().value
            noteDate.text =if (year==current) localDate.format(DateTimeFormatter.ofPattern("dd MMM"))
                          else localDate.format(DateTimeFormatter.ofPattern("dd MMM yy"))
            root.setOnClickListener {
                listener?.onClicked(note)
            }
        }
    }

    fun setNoteListener(listener:OnNoteClicked){
        this.listener =listener
    }
}