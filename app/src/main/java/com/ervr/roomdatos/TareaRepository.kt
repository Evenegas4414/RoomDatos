package com.ervr.roomdatos

import androidx.lifecycle.LiveData

class TareaRepository(private val tareaDao: TareaDao) {
    val leerTodasLasTareas: LiveData<List<Tarea>> = tareaDao.leerTodasLasTareas()

    suspend fun agregarTarea(tarea: Tarea) {
        tareaDao.agregarTarea(tarea)
    }

    suspend fun actualizarTarea(tarea: Tarea) {
        tareaDao.actualizarTarea(tarea)
    }

    suspend fun borrarTarea(tarea: Tarea) {
        tareaDao.borrarTarea(tarea)
    }

    suspend fun borrarTodo(): Int {
        return tareaDao.borrarTodo()
    }
}

