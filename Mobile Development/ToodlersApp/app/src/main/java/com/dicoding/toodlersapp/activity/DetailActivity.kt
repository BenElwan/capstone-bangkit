package com.dicoding.toodlersapp.activity

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.dicoding.toodlersapp.data.Resep
import com.dicoding.toodlersapp.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val foto = binding.imgFoto
        val namaResep = binding.namaResep
        val deskripsi = binding.deskripsiResep
        val nilai = binding.resep
        val porsi = binding.porsi
        val bahan = binding.bahan
        val cara = binding.cara

        val detailResep = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(MainActivity.EXTRA_RESEP, Resep::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(MainActivity.EXTRA_RESEP)
        }

        if (detailResep != null) {
            Glide.with(this)
                .load(detailResep.photo)
                .into(foto)
            namaResep.text = detailResep.namaResep
            deskripsi.text = detailResep.deskripsi
            nilai.text = detailResep.nilai
            porsi.text = detailResep.porsi
            bahan.text = detailResep.bahan
            cara.text = detailResep.cara
        }
    }
}