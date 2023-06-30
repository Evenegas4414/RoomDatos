package com.ervr.roomdatos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TareaViewModel(private val repository: TareaRepository) : ViewModel() {
    val leerTodasLasTareas = repository.leerTodasLasTareas

    fun agregarTarea(tarea: Tarea) = viewModelScope.launch(Dispatchers.IO) {
        repository.agregarTarea(tarea)
    }

    fun actualizarTarea(tarea: Tarea) = viewModelScope.launch(Dispatchers.IO) {
        repository.actualizarTarea(tarea)
    }

    fun borrarTarea(tarea: Tarea) = viewModelScope.launch(Dispatchers.IO) {
        repository.borrarTarea(tarea)
    }

    fun borrarTodo() = viewModelScope.launch(Dispatchers.IO) {
        repository.borrarTodo()
    }
}
