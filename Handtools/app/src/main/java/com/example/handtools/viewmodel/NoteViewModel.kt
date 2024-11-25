package com.example.handtools.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.handtools.data.Note
import com.example.handtools.data.NoteRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class NoteViewModel(private val repository: NoteRepository) : ViewModel() {

    private val _allNotes = MutableStateFlow<List<Note>>(emptyList())
    val allNotes: StateFlow<List<Note>> get() = _allNotes

    init {
        getAllNotes()
    }

    private fun getAllNotes() = viewModelScope.launch {
        repository.getAllNotes().collect {
            _allNotes.value = it
        }
    }

    fun insert(note: Note) {
        viewModelScope.launch {
            repository.insert(note) // Inserta la nueva nota
            refreshNotes() // Refresca la lista de notas
        }
    }

    fun update(note: Note) {
        viewModelScope.launch {
            repository.update(note) // Actualiza la nota
            refreshNotes() // Refresca la lista de notas
        }
    }

    private fun refreshNotes() {
        // Recolectar el Flow para obtener la lista de notas
        viewModelScope.launch {
            // El Flow se recolecta aquí, obteniendo la lista de notas
            repository.getAllNotes().collect { notes ->
                _allNotes.value = notes // Actualizar el estado de la lista de notas
            }
        }
    }

    fun delete(note: Note) = viewModelScope.launch {
        repository.delete(note)
    }
}