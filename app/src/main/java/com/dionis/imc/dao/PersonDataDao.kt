package com.dionis.imc.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.dionis.imc.model.PersonData

@Dao
interface PersonDataDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(personData: PersonData)

    @Delete
    suspend fun remove(personData: PersonData)

    @Update
    suspend fun update(personData: PersonData)


    @Query("SELECT * FROM table_person" )
    fun getAll() : LiveData<List<PersonData>>

    @Query("SELECT * FROM table_person WHERE id = :key" )
    fun get(key: Int) : PersonData


}