package com.example.handtools.data

import kotlinx.coroutines.flow.Flow

class NoteRepository(private val noteDao: NoteDao) {
    suspend fun insert(note: Note){
        noteDao.insert(note)
    }

    fun getAllNotes(): Flow<List<Note>> = noteDao.getAllNotes()

    suspend fun update(note: Note){
        noteDao.update(note)
    }

    suspend fun delete(note: Note) {
        noteDao.delete(note)
    }
}