package com.ervr.roomdatos

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TareaDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun agregarTarea(tarea: Tarea)

    @Update
    suspend fun actualizarTarea(tarea: Tarea)

    @Delete
    suspend fun borrarTarea(tarea: Tarea)

    @Query("DELETE FROM tareas_table")
    suspend fun borrarTodo(): Int

    @Query("SELECT * FROM tareas_table ORDER BY id DESC")
    fun leerTodasLasTareas(): LiveData<List<Tarea>>
}

