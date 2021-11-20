package mx.uaa.mafl.projecttest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView

/*Adaptador para el recyclerview que contiente los mensajes predeterminados de juego*/

class RecViewAdapter: RecyclerView.Adapter<RecViewAdapter.myViewHolder>() {

    private lateinit var mListener: onItemClickListener

    interface onItemClickListener{

        fun onItemClick(position : Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener){
        mListener = listener

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recview_content, parent, false)
        return myViewHolder(view,mListener)
    }

    override fun onBindViewHolder(holder: RecViewAdapter.myViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 3
    }
    class myViewHolder(itemView: View, listener: onItemClickListener): RecyclerView.ViewHolder(itemView){

        init {

            itemView.setOnClickListener {

                listener.onItemClick(adapterPosition)

            }
        }
    }
}