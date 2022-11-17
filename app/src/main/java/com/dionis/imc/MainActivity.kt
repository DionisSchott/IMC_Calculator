package com.dionis.imc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.dionis.imc.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var result: Float? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUp()
    }

    private fun setUp() {
        setUpClicks()

    }

    private fun setUpClicks() {
        binding.btCalcular.setOnClickListener {
            validaForm()
        }
    }

    private fun validaForm(): Boolean {

        var error = false

        if (binding.altura.text.isEmpty()) {
            binding.altura.error = "digite sua altura"
            Toast.makeText(this, "NÃO FOI POSSÍVEL CALCULAR", Toast.LENGTH_LONG).show()
            error = true
        }; if (binding.peso.text.isEmpty()) {
            binding.peso.error = "digite seu peso"
            Toast.makeText(this, "NÃO FOI POSSÍVEL CALCULAR", Toast.LENGTH_LONG).show()
            error = true
        }
        else {
            calculateImc()
            irParaResult()
        }

        return error
    }

    private fun irParaResult() {

        val segundaTela = Intent(this, ResultActivity2::class.java)

        segundaTela.putExtra("result", result)

        startActivity(segundaTela)
    }

    private fun calculateImc() {
        val peso = binding.peso.text.toString().toFloat()
        val alt = binding.altura.text.toString().toFloat()
        val resultado = peso / (alt * alt)

        result = String.format("%.2f", resultado).toFloat()

    }

    override fun onBackPressed() {
        finishAffinity()
        finish()
    }


}







