package com.ervr.roomdatos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: TareaViewModel
    private lateinit var tareaAdapter: TareaAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        tareaAdapter = TareaAdapter {tarea -> showDialog(tarea)}
        recyclerView.adapter = tareaAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        val dao = TareaDatabase.getDatabase(application).tareaDao()
        val repository = TareaRepository(dao)
        val factory = TareaViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory).get(TareaViewModel::class.java)

        viewModel.leerTodasLasTareas.observe(this, Observer { tareas ->
            tareaAdapter.setData(tareas)
        })

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            showDialog(null)
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

    private fun showDialog(tarea: Tarea?) {
        val builder = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val dialogLayout = inflater.inflate(R.layout.dialogo_tarea, null)
        val editTextName = dialogLayout.findViewById<EditText>(R.id.editarNombre)
        val editTextDescription = dialogLayout.findViewById<EditText>(R.id.editarDescripcion)

        with(builder) {
            setTitle(if (tarea == null) "Nueva tarea" else "Editar tarea")
            setPositiveButton("Aceptar") { dialog, _ ->
                val nombre = editTextName.text.toString()
                val descripcion = editTextDescription.text.toString()
                if (tarea == null) {
                    viewModel.agregarTarea(Tarea(0, nombre, descripcion))
                } else {
                    tarea.nombre = nombre
                    tarea.descripcion = descripcion
                    viewModel.actualizarTarea(tarea)
                }
                dialog.dismiss()
            }
            setNegativeButton("Cancelar") { dialog, _ ->
                dialog.dismiss()
            }
            setView(dialogLayout)
            show()
        }
    }
}