package com.ervr.roomdatos

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tareas_table")
data class Tarea(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val nombre: String,
    val descripcion: String
)
