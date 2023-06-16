package com.dicoding.toodlersapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.toodlersapp.R
import com.dicoding.toodlersapp.data.Resep

class ResepAdapter(private val listResep: List<Resep>) : RecyclerView.Adapter<ResepAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback : OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_resep, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (foto, nama, deskripsi) = listResep[position]
        Glide.with(holder.itemView.context)
            .load(foto)
            .into(holder.imgFoto)
        holder.tvNama.text = nama
        holder.tvDeskripsi.text = deskripsi
        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listResep[holder.adapterPosition]) }
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgFoto: ImageView = itemView.findViewById(R.id.img_foto)
        val tvNama: TextView = itemView.findViewById(R.id.nama_resep)
        val tvDeskripsi: TextView = itemView.findViewById(R.id.deskripsi_resep)
    }

    override fun getItemCount(): Int = listResep.size

    interface OnItemClickCallback {
        fun onItemClicked(data: Resep)
    }
}