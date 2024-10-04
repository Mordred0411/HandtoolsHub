package com.example.handtools.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface NoteDao {
    @Insert
    suspend fun  insert(note: Note)

    @Query("SELECT * FROM notes ORDER BY createdAt DESC")
    suspend fun getAllNotes(): List<Note>

    @Update
    suspend fun update(note: Note)

    @Delete
    suspend fun delete(note: Note)
}