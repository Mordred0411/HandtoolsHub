package com.example.handtools.data

class NoteRepository(private val noteDao: NoteDao) {
    suspend fun insert(note: Note){
        noteDao.insert(note)
    }

    suspend fun getAllNotes(): List<Note> {
        return noteDao.getAllNotes()
    }

    suspend fun update(note: Note){
        noteDao.update(note)
    }

    suspend fun delete(note: Note) {
        noteDao.delete(note)
    }
}