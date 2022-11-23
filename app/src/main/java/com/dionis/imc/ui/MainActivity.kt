package com.dionis.imc.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.dionis.imc.databinding.ActivityMainBinding
import com.dionis.imc.model.PersonData
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private var result: Float? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUp()
    }


    private fun setUp() {
        setUpClicks()
        setupAdapters()

    }

    private fun setUpClicks() {
        binding.btCalcular.setOnClickListener {
            validateFields()
        }
    }


    private fun setupAdapters() {
        val adapter = PersonAdapter()
        val recyclerView = binding.rvPerson

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)


        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel._readAllData.observe(this, Observer { person ->
            adapter.updateList(person)
        })


    }

    private fun validateFields(): Boolean {

        var error = false

        if (binding.etAltura.text.isEmpty()) {
            binding.etAltura.error = "digite sua altura"
            Toast.makeText(this, "NÃO FOI POSSÍVEL CALCULAR", Toast.LENGTH_LONG).show()
            error = true
        }; if (binding.etPeso.text.isEmpty()) {
            binding.etPeso.error = "digite seu peso"
            Toast.makeText(this, "NÃO FOI POSSÍVEL CALCULAR", Toast.LENGTH_LONG).show()
            error = true

        } else {
            calculateImc()
            saveData()
            irParaResult()
        }

        return error
    }

    private fun saveData() {
        var nome = binding.etNome.text.toString()
        var peso = binding.etPeso.text.toString().toFloat()
        var altura = binding.etAltura.text.toString().toFloat()
        var resultado = result.toString().toFloat()

        viewModel.save(nome, altura, peso, resultado)
    }

    private fun irParaResult() {

        val segundaTela = Intent(this, ResultActivity2::class.java)

        segundaTela.putExtra("result", result)


        startActivity(segundaTela)
    }



    private fun calculateImc() {
        val peso = binding.etPeso.text.toString().toFloat()
        val alt = binding.etAltura.text.toString().toFloat()
        val resultado = peso / (alt * alt)

        result = String.format("%.2f", resultado).toFloat()

    }

    override fun onBackPressed() {
        finishAffinity()
        finish()
    }


}







