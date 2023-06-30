package com.ervr.roomdatos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TareaAdapter(private val onItemClicked: (Tarea) -> Unit) : RecyclerView.Adapter<TareaAdapter.TareaViewHolder>() {

    private var listaTareas = emptyList<Tarea>()

    inner class TareaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNombre: TextView = itemView.findViewById(R.id.tvNombre)
        val tvDescripcion: TextView = itemView.findViewById(R.id.tvDescripcion)

        fun bind(tarea: Tarea) {
            tvNombre.text = tarea.nombre
            tvDescripcion.text = tarea.descripcion
            itemView.setOnClickListener {
                onItemClicked(tarea)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TareaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_tarea, parent, false)
        return TareaViewHolder(view)
    }

    override fun onBindViewHolder(holder: TareaViewHolder, position: Int) {
        holder.bind(listaTareas[position])
    }

    override fun getItemCount(): Int {
        return listaTareas.size
    }

    fun setData(tareas: List<Tarea>) {
        this.listaTareas = tareas
        notifyDataSetChanged()
    }
}
