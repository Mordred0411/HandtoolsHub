package com.example.handtools.iu

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.handtools.viewmodel.NoteViewModel
import com.example.handtools.data.Note


@Composable
fun NotesScreen(noteViewModel: NoteViewModel = viewModel()) {
    val allNotes by noteViewModel.allNotes.observeAsState(emptyList())

    Scaffold (
        topBar = {
            TopAppBar(
                title = { Text(text = "Notes")}
            )
        }
    ) {
        NotesList(notes = allNotes)
    }
}

@Composable
fun NotesList(notes: List<Note>) {
    LazyColumn (
        contentPadding = PaddingValues(horizontal = 16.dp,vertical = 8.dp)
    ) { items(notes) { note ->
        NoteItem(note)

    } }
}

@Composable
fun NoteItem(note: Note) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
            .Padding(vertical = 4.dp),
        elevation = 2.dp
    ) @androidx.compose.runtime.Composable {
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
        }
    }
}