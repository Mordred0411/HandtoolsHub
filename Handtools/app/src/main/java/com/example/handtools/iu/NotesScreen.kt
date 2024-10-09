package com.example.handtools.iu

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.handtools.viewmodel.NoteViewModel
import com.example.handtools.data.Note
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import com.example.handtools.data.NoteRepository
import com.example.handtools.viewmodel.NoteViewModelFactory


@Composable
fun NotesScreen(repository: NoteRepository) {

    val noteViewModel: NoteViewModel = viewModel(factory = NoteViewModelFactory(repository))

    val allNotes by noteViewModel.allNotes.observeAsState(emptyList())

    @OptIn(ExperimentalMaterial3Api::class)
    Scaffold (
        topBar = {
            TopAppBar(
                title = { Text(text = "Notes")}
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                val newNote = Note(
                    title = "New Note",
                    content = "This is a new note."
                )
                noteViewModel.insert(newNote)
            }) {
                Icon(Icons.Default.Add, contentDescription = "Add Note")
            }
        }
    ) { paddingValues ->
        NotesList(notes = allNotes, modifier = Modifier.padding(paddingValues))
    }
}

@Composable
fun NotesList(notes: List<Note>, modifier: Modifier) {
    LazyColumn (
        contentPadding = PaddingValues(horizontal = 16.dp,vertical = 8.dp)
    ) {
        items(notes) { note ->
            NoteItem(note)
        }
    }
}

@Composable
fun NoteItem(note: Note) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = note.title, style = MaterialTheme.typography.titleSmall)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = note.content, style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Created: ${note.createdAt})",
                style = MaterialTheme.typography.displaySmall
            )
        }
    }
}