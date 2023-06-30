package com.ervr.roomdatos

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tareas_table")
data class Tarea(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    var nombre: String,
    var descripcion: String
)
