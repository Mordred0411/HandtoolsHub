package com.example.handtools.iu


import kotlinx.coroutines.flow.Flow
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.handtools.viewmodel.NoteViewModel
import com.example.handtools.data.Note
import com.example.handtools.data.NoteRepository
import com.example.handtools.viewmodel.NoteViewModelFactory


@Composable
fun NotesScreen(noteViewModel: NoteViewModel) {
    val allNotes by noteViewModel.allNotes.collectAsState(initial = emptyList())
    var isEditing by remember { mutableStateOf(false) }
    var noteToEdit by remember { mutableStateOf<Note?>(null) }

    // Mostrar el editor si se está creando/editando una nota
    if (isEditing) {
        NoteEditorDialog(
            note = noteToEdit,
            onSave = { note ->
                if (note.id == null) {
                    noteViewModel.insert(note)
                } else {
                    noteViewModel.update(note)
                }
                isEditing = false
            },
            onDismiss = { isEditing = false }
        )
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                noteToEdit = null // Crear nueva nota
                isEditing = true
            }) {
                Icon(Icons.Default.Add, contentDescription = "Add Note")
            }
        }
    ) { padding ->
        NotesList(
            notes = allNotes,
            onEdit = { note ->
                noteToEdit = note
                isEditing = true
            },
            onDelete = { note ->
                noteViewModel.delete(note)
            },
            modifier = Modifier.padding(padding)
        )
    }
}

@Composable
fun NotesList(
    notes: List<Note>,
    onEdit: (Note) -> Unit,
    onDelete: (Note) -> Unit,
    modifier: Modifier
) {
    LazyColumn(
        modifier.padding(vertical = 20.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        items(notes) { note ->
            NoteItem(note, onEdit = onEdit, onDelete = onDelete)
        }
    }
}