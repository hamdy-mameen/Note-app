package com.example.notesapp.presentation.notes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.Note
import com.example.notesapp.R
import com.example.notesapp.databinding.NotesFragmentBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class NotesFragment : Fragment(R.layout.notes_fragment),OnNoteClicked{
    private val viewModel:NotesViewModel by activityViewModels()
    private var _binding:NotesFragmentBinding?=null
    private val binding get() = _binding!!
    @Inject
    lateinit var notesAdapter: NotesAdapter
     private var tester =1
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = NotesFragmentBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerview()
        notesAdapter.setNoteListener(this)
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.notesState.collect{
                    notesAdapter.differ.submitList(it)
                    if(it.isEmpty()){
                        binding.emptyState.root.visibility =View.VISIBLE
                        binding.notesList.visibility =View.GONE
                    }else{
                        if(!binding.notesList.isVisible and binding.emptyState.root.isVisible){
                            binding.notesList.visibility =View.VISIBLE
                            binding.emptyState.root.visibility =View.GONE
                        }

                    }
                }
            }
        }
        binding.addNoteBtn.setOnClickListener {
                findNavController().navigate(NotesFragmentDirections.actionNotesFragmentToNewNoteFragment(null))
            }


    }
    private val itemTouchCallback= object:ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT){
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
           return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
           val position = viewHolder.adapterPosition
           val note = notesAdapter.differ.currentList[position]
           // tester =1
            viewModel.deleteNote(note)
            Snackbar.make(requireView(),getString(R.string.snack_delete_msg),Snackbar.LENGTH_LONG).apply {

                setAction(getString(R.string.snack_undo_msg)){
                    viewModel.addNote(note)
                    //tester =0
                }
                show()
               /* if(tester==1) {
                    viewModel.deleteNote(note)
                }*/
            }

        }


    }
    private fun setupRecyclerview(){
        binding.notesList.apply {
                layoutManager = LinearLayoutManager(requireContext()).apply {
                    reverseLayout = true
                    stackFromEnd = true
                }
                adapter = notesAdapter
                ItemTouchHelper(itemTouchCallback).attachToRecyclerView(this)


            }
    }


    override fun onClicked(note: Note) {
        findNavController().navigate(NotesFragmentDirections.actionNotesFragmentToNewNoteFragment(note))
    }
}