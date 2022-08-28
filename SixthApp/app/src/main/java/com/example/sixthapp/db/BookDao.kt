package com.example.sixthapp.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Flowable

@Dao
interface BookDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(book: Book)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(vararg books: Book)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(books: List<Book>)

    @Query("SELECT author FROM Book")
    fun selectALL(): List<String>

    @Query("SELECT * FROM Book")
    fun selectAll(): List<Book>

    @Query("SELECT * FROM book WHERE year >= 2000 AND year <= 2022 ORDER BY year ASC")
    fun selectModern(): Flowable<List<Book>>

    @Query("DELETE FROM Book")
    fun clearTable()
}