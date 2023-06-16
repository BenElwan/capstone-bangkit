package com.dicoding.toodlersapp.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.toodlersapp.R
import com.dicoding.toodlersapp.adapter.ResepAdapter
import com.dicoding.toodlersapp.data.Resep
import com.dicoding.toodlersapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ResepAdapter
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        autoPorsi()
        resepList()
        binding.cari.setOnClickListener{
            getRekomendasi()
        }
    }

   private fun autoPorsi(){
        val jenisKelamin = resources.getStringArray(R.array.porsi)
        val autoCompleteJK : AutoCompleteTextView = findViewById(R.id.autoCompletePorsi)
        val adapterJK = ArrayAdapter(this, R.layout.item_dropdown, jenisKelamin)
        autoCompleteJK.setAdapter(adapterJK)
        binding.autoCompletePorsi.hint = "Pilih Porsi Makan Anak..."

        binding.inputPorsi.setOnClickListener{
            binding.autoCompletePorsi.hint = ""
        }
    }

    private fun getRekomendasi(){
        val beratBadan = binding.edBeratBadan.text.toString()
        val usia = binding.edUsia.text.toString()
        var porsi = binding.autoCompletePorsi.text.toString()
        when{
            beratBadan.isEmpty() -> {
                Toast.makeText(this, "Masukkan Berat Badan Anak.", Toast.LENGTH_SHORT).show()
            }
            usia.isEmpty() -> {
                Toast.makeText(this, "Masukkan Usia Anak.", Toast.LENGTH_SHORT).show()
            }
            porsi.isEmpty() -> {
                Toast.makeText(this, "Masukkan Porsi Makan Anak.", Toast.LENGTH_SHORT).show()
            }
            else -> {
                binding.titles.text = "Rekomendasi Menu"

                if(beratBadan.toDouble() == 6.4 && usia.toInt() == 6 && porsi == "2 kali makanan utama"){
                    rekomendasiList(0, 2)
                }else if(beratBadan.toDouble() > 6.4 && beratBadan.toDouble() < 7.4 && usia.toInt() == 6 && porsi == "2 kali makanan utama"){

                    val x = (beratBadan.toDouble() - 6.4) * 10
                    val y = x - 1
                    val rekomendasi = x + y + 2
                    rekomendasiList(rekomendasi.toInt(), rekomendasi.toInt() + 2)
                }else if(beratBadan.toDouble() == 6.8 && usia.toInt() > 6 && usia.toInt() < 9 && porsi == "3 kali makanan utama"){
                    rekomendasiList(20, 22)
                }else if(beratBadan.toDouble() > 6.8 && beratBadan.toDouble() < 7.8 && usia.toInt() > 6 && usia.toInt() < 9 && porsi == "3 kali makanan utama"){
                    val x = (beratBadan.toDouble() - 6.8) * 10
                    val y = x - 1
                    val rekomendasi = x + y + 2
                    rekomendasiList(rekomendasi.toInt() + 20, rekomendasi.toInt() + 22)
                }else if(beratBadan.toDouble() == 7.3 && usia.toInt() > 8 && usia.toInt() < 12 && porsi == "3 kali makanan utama dan 1 kali makanan snack"){
                    rekomendasiList( 40, 42)
                }else if(beratBadan.toDouble() > 7.3 && beratBadan.toDouble() < 8.3 && usia.toInt() > 8 && usia.toInt() < 12 && porsi == "3 kali makanan utama dan 1 kali makanan snack"){
                    val x = (beratBadan.toDouble() - 7.3) * 10
                    val y = x - 1
                    val rekomendasi = x + y + 2
                    rekomendasiList(rekomendasi.toInt() + 40, rekomendasi.toInt() + 42)
                }else if(beratBadan.toDouble() == 7.9 && usia.toInt() > 11 && usia.toInt() < 25 && porsi == "3 kali makanan utama dan 2 kali makanan snack"){
                    rekomendasiList( 60, 62)
                }else if(beratBadan.toDouble() > 7.9 && beratBadan.toDouble() < 8.3 && usia.toInt() > 11 && usia.toInt() < 25 && porsi == "3 kali makanan utama dan 2 kali makanan snack"){
                    val x = (beratBadan.toDouble() - 7.9) * 10
                    val y = x - 1
                    var rekomendasi = x + y + 2
                    rekomendasiList(rekomendasi.toInt() + 60, rekomendasi.toInt() + 62)
                }else if(beratBadan.toDouble() > 8.2 && beratBadan.toDouble() < 8.5 && usia.toInt() > 11 && usia.toInt() < 25 && porsi == "3 kali makanan utama dan 2 kali makanan snack"){
                    val x = (beratBadan.toDouble() - 7.9) * 10
                    val y = x - 1
                    var rekomendasi = x + y + 1
                    rekomendasiList(rekomendasi.toInt() + 60, rekomendasi.toInt() + 62)
                }else if(beratBadan.toDouble() > 8.4 && beratBadan.toDouble() < 8.8 && usia.toInt() > 11 && usia.toInt() < 25 && porsi == "3 kali makanan utama dan 2 kali makanan snack"){
                    val x = (beratBadan.toDouble() - 7.9) * 10
                    val y = x - 1
                    var rekomendasi = x + y + 2
                    rekomendasiList(rekomendasi.toInt() + 60, rekomendasi.toInt() + 62)
                }else if(beratBadan.toDouble() == 8.8 && usia.toInt() > 11 && usia.toInt() < 25 && porsi == "3 kali makanan utama dan 2 kali makanan snack"){
                    rekomendasiList( 79, + 81)
                }else{
                    showMessage(true)
                    binding.btnRefresh.setOnClickListener{
                        resepList()
                    }
                    binding.cari.setOnClickListener{
                        binding.titles.text = ""
                        showMessage(false)
                        getRekomendasi()
                    }
                }
            }
        }
    }

    fun resepList(){
        binding.titles.text = "Resep MPASI"
        mainViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            MainViewModel::class.java)

        adapter = ResepAdapter(mainViewModel.getAllResep())

        binding.apply {
            rvResep.layoutManager = LinearLayoutManager(this@MainActivity)
            rvResep.setHasFixedSize(true)
            rvResep.adapter = adapter
        }

        showMessage(false)

        adapter.setOnItemClickCallback(object : ResepAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Resep) {
                showSelectedResep(data)
            }
        })
    }

    fun rekomendasiList(x: Int,y: Int) {
        mainViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            MainViewModel::class.java)

        adapter = ResepAdapter(mainViewModel.getRekomendasi().subList(x, y))
        binding.apply {
            rvResep.layoutManager = LinearLayoutManager(this@MainActivity)
            rvResep.setHasFixedSize(true)
            rvResep.adapter = adapter
        }

        adapter.setOnItemClickCallback(object : ResepAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Resep) {
                showSelectedResep(data)
            }
        })
    }

    private fun showSelectedResep(resep: Resep) {
        val moveIntent = Intent(this, DetailActivity::class.java)
        moveIntent.putExtra(EXTRA_RESEP, resep)
        startActivity(moveIntent)
    }

    private fun showMessage(getRekomendasi: Boolean){
        if(getRekomendasi){
            binding.noData.visibility = View.VISIBLE
            binding.btnRefresh.visibility = View.VISIBLE
            binding.rvResep.visibility = View.GONE
        }else{
            binding.noData.visibility = View.GONE
            binding.btnRefresh.visibility = View.GONE
            binding.rvResep.visibility = View.VISIBLE
        }
    }

    companion object{
        const val EXTRA_RESEP = "extra_resep"
    }
}