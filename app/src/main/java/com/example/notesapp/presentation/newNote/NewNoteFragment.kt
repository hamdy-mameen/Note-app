package com.example.notesapp.presentation.newNote

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.domain.model.Note
import com.example.notesapp.R
import com.example.notesapp.databinding.NewNoteFragmentBinding
import com.example.notesapp.presentation.notes.NotesViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@AndroidEntryPoint
class NewNoteFragment : Fragment(R.layout.new_note_fragment) {
    private val viewModel: NotesViewModel by activityViewModels()
    private var _binding:NewNoteFragmentBinding?=null
    private val binding get() = _binding!!
    private val args:NewNoteFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = NewNoteFragmentBinding.inflate(inflater,container,false)
        args.note?.let {note->
            binding.etTitle.setText(note.title)
            binding.etDescription.setText(note.description)

        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val formatter = DateTimeFormatter.ofPattern("dd MMM YYYY")
        val current = LocalDateTime.now().format(formatter)
        binding.txtDate.text =current
        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(binding.etTitle.text.trim().isNotEmpty() && binding.etDescription.text.trim().isNotEmpty()){
                    binding.btnSave.setImageResource(R.drawable.baseline_check_24)
                }else{
                    binding.btnSave.setImageResource(R.drawable.baseline_uncheck_24)
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }
        }
        binding.etTitle.addTextChangedListener(textWatcher)
        binding.etDescription.addTextChangedListener(textWatcher)

            binding.btnClose.setOnClickListener {
                findNavController().navigate(NewNoteFragmentDirections.actionNewNoteFragmentToNotesFragment())
            }

        binding.btnSave.setOnClickListener {
                if (binding.etTitle.text.isEmpty() or binding.etDescription.text.isEmpty()){
                    return@setOnClickListener
                }
                if(args.note==null) {
                    viewModel.addNote(
                        Note(title = binding.etTitle.text.toString(),
                        description = binding.etDescription.text.toString(), date = System.currentTimeMillis())
                    )
                    Snackbar.make(view, "Note Saved", Snackbar.LENGTH_SHORT).show()
                }else{
                    viewModel.updateNote(Note(args.note!!.id,binding.etTitle.text.toString(),binding.etDescription.text.toString(),args.note!!.date))
                }
                findNavController().navigate(NewNoteFragmentDirections.actionNewNoteFragmentToNotesFragment())
            }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }
}