package com.dionis.imc.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "table_person")
data class PersonData(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "person_data")
    val nome: String,
    val altura: Float,
    val peso: Float,
    val resultado: Float
)
