package com.dionis.imc.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.View
import com.dionis.imc.R
import com.dionis.imc.databinding.ActivityResult2Binding

class ResultActivity2 : AppCompatActivity() {

    private lateinit var binding: ActivityResult2Binding
    private lateinit var result: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityResult2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        setUp()


    }

    private fun setUp() {
        recuperaDados()
        definition()
        setupClicks()
        openLink()
        hideLink()
    }

    private fun setupClicks() {
        binding.btRecalcular.setOnClickListener {
            irParaMain()
        }
    }

    private fun recuperaDados() {

        result = intent.getFloatExtra("result", 0F).toString()
        binding.resultado.setText(result)

    }


    private fun irParaMain() {
        val primeiraTela = Intent(this, MainActivity::class.java)
        startActivity(primeiraTela)

    }


    private fun definition() {

        val grau: Double = result.toDouble()

        when (grau) {
            in grau..15.9 -> {
                binding.definicao.setText("desnutrição nível III,\n Procure orientação médica")
                binding.resultado.setTextColor(getColor(R.color.warning))
            }

            in grau..16.9 -> {
                binding.definicao.text = "desnutrição nível II,\n Procure orientação médica"
                binding.resultado.setTextColor(getColor(R.color.preWarning))
            }

            in grau..18.4 -> {
                binding.definicao.text = "desnutrição nível I,\n Alimente-se bem"
                binding.resultado.setTextColor(getColor(R.color.preWarning))
            }

            in grau..24.9 -> {
                binding.definicao.text = "seu peso está normal"
                binding.resultado.setTextColor(getColor(R.color.good))
            }

            in grau..29.9 -> {
                binding.definicao.text = "pré obesidade,\n cuide da sua alimentação"
                binding.resultado.setTextColor(getColor(R.color.preWarning))
            }

            in grau..34.5 -> {
                binding.definicao.text = "OBESIDADE GRAU I,\n cuide da sua alimentação"
                binding.resultado.setTextColor(getColor(R.color.preWarning))
            }

            in grau..39.9 -> {
                binding.definicao.text = "OBESIDADE GRAU II,\n Procure orientação médica"
                binding.resultado.setTextColor(getColor(R.color.warning))
            }

            else -> {
                binding.definicao.text = "OBESIDADE GRAU III,\n Procure orientação médica"
                binding.resultado.setTextColor(getColor(R.color.warning))
            }
        }
    }

    private fun openLink() {

        binding.tvImcInfo.movementMethod = LinkMovementMethod.getInstance()
        binding.tvImcInfo.setLinkTextColor(getColor(R.color.blue))

        binding.tvObesidadeDesnutricao.movementMethod = LinkMovementMethod.getInstance()
        binding.tvObesidadeDesnutricao.setLinkTextColor(getColor(R.color.blue))

        binding.tvObesidade.movementMethod = LinkMovementMethod.getInstance()
        binding.tvObesidade.setLinkTextColor(getColor(R.color.blue))

    }

    private fun hideLink() {
        if (result.toDouble() < 25) {
            binding.tvObesidade.visibility = View.GONE
        }
    }

    override fun onBackPressed() {
        startActivity(Intent(this, MainActivity::class.java))
    }


}


