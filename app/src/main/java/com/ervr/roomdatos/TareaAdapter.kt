package com.ervr.roomdatos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TareaAdapter : RecyclerView.Adapter<TareaAdapter.TareaViewHolder>() {

    private var listaTareas = emptyList<Tarea>()

    class TareaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNombre: TextView = itemView.findViewById(R.id.tvNombre)
        val tvDescripcion: TextView = itemView.findViewById(R.id.tvDescripcion)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TareaViewHolder {
        return TareaViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_tarea, parent, false))
    }

    override fun onBindViewHolder(holder: TareaViewHolder, position: Int) {
        val tareaActual = listaTareas[position]
        holder.tvNombre.text = tareaActual.nombre
        holder.tvDescripcion.text = tareaActual.descripcion
    }

    override fun getItemCount(): Int {
        return listaTareas.size
    }

    fun setData(tareas: List<Tarea>) {
        this.listaTareas = tareas
        notifyDataSetChanged()
    }
}