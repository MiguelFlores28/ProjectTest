package mx.uaa.mafl.projecttest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/*Adaptador para el recyclerview que contiente los mensajes predeterminados de juego*/

class RecViewAdapter: RecyclerView.Adapter<RecViewAdapter.myViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recview_content, parent, false)
        return myViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecViewAdapter.myViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 20
    }
    class myViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
}