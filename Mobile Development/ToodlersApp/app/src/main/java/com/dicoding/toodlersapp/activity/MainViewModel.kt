package com.dicoding.toodlersapp.activity

import androidx.lifecycle.ViewModel
import com.dicoding.toodlersapp.data.ListResep
import com.dicoding.toodlersapp.data.Resep
import com.dicoding.toodlersapp.data.SemuaResep

class MainViewModel: ViewModel() {
    private val listResep = mutableListOf<Resep>()
    private val listRekomendasi = mutableListOf<Resep>()

    init {
        if (listResep.isEmpty()) {
            SemuaResep.semuaResep.forEach {
                listResep.add(it)
            }
        }

        if(listRekomendasi.isEmpty()){
            ListResep.listResep.forEach{
                listRekomendasi.add(it)
            }
        }
    }

    fun getAllResep(): List<Resep>{
        return listResep
    }

    fun getRekomendasi(): List<Resep>{
        return listRekomendasi
    }
}