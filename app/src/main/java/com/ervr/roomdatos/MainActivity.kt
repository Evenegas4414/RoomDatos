package com.ervr.roomdatos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: TareaViewModel
    private lateinit var tareaAdapter: TareaAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializamos RecyclerView y Adapter
        recyclerView = findViewById(R.id.recyclerView)
        tareaAdapter = TareaAdapter()
        recyclerView.adapter = tareaAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        val dao = TareaDatabase.getDatabase(application).tareaDao()
        val repository = TareaRepository(dao)
        val factory = TareaViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory).get(TareaViewModel::class.java)

        // Observamos los cambios en los datos
        viewModel.leerTodasLasTareas.observe(this, Observer { tareas ->
            tareaAdapter.setData(tareas)
        })

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            // Aquí puedes abrir una nueva Activity o un Dialog para agregar una tarea
            // Por ahora, sólo añadiremos una tarea de ejemplo
            viewModel.agregarTarea(Tarea(0, "Tarea de ejemplo", "Descripción de la tarea"))
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_borrar_todo -> {
                viewModel.borrarTodo()
                Toast.makeText(this, "Todas las tareas borradas", Toast.LENGTH_SHORT).show()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}