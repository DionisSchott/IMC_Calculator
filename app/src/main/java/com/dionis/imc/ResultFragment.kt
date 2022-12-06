package com.dionis.imc

import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.dionis.imc.databinding.FragmentResultBinding
import com.dionis.imc.model.PersonData
import com.dionis.imc.ui.MainViewModel
import com.dionis.imc.ui.PersonAdapter


class ResultFragment : Fragment() {

    private lateinit var personAdapter: PersonAdapter
    private lateinit var viewModel: MainViewModel
    private lateinit var binding: FragmentResultBinding
    private var result: Float = 0.0f
    private lateinit var dados: PersonData


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentResultBinding.inflate(inflater, container, false)

        result = arguments?.getFloat(PERSON_RESULT) as Float
        dados = arguments?.getSerializable(DATA) as PersonData

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUp()
    }

    private fun setUp() {
        delete()
        definition()
        setupClicks()
        openLink()
        hideLink()
    }


    private fun delete() {

        personAdapter = PersonAdapter()
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        binding.btDelete.setOnClickListener {
            viewModel.delete(dados)
            findNavController().popBackStack()
        }

    }

    private fun setupClicks() {
        binding.btRecalcular.setOnClickListener {
            findNavController().navigate(R.id.action_resultFragment_to_homeFragment)
        }
    }


    private fun setDefinitionText(definition: String, tip: String, color: Int) {
        binding.definicao.text = definition.plus(", \n").plus(tip)
        binding.resultado.setTextColor(resources.getColor(color))
    }

    private fun definition() {

        binding.resultado.text = result.toString()

        val grau: Double = result.toDouble()

        when (grau) {
            in grau..15.9 -> {
                setDefinitionText("Desnutrição nível III", "Procure orientação médica", R.color.warning)
            }

            in grau..16.9 -> {
                setDefinitionText("Desnutrição nível II", "Procure orientação médica", R.color.preWarning)
            }

            in grau..18.4 -> {
                setDefinitionText("Desnutrição nivel I", "Alimente-se bem", R.color.preWarning)
            }

            in grau..24.9 -> {
                setDefinitionText("Seu peso está normal", "", R.color.good)
            }

            in grau..29.9 -> {
                setDefinitionText("Pré obesidade", "cuide da sua alimentação", R.color.preWarning)
            }

            in grau..34.5 -> {
                setDefinitionText("Obesidade grau I", "Cuide da sua alimentação", R.color.preWarning)
            }

            in grau..39.9 -> {
                setDefinitionText("Obesidade grau II", "Procure orientação médica", R.color.warning)
            }

            else -> {
                setDefinitionText("Obesidade grau III", "Procure orientação médica", R.color.warning)
            }
        }
    }


    private fun openLink() {

        binding.tvImcInfo.movementMethod = LinkMovementMethod.getInstance()
        binding.tvImcInfo.setLinkTextColor(resources.getColor(R.color.blue))

        binding.tvObesidadeDesnutricao.movementMethod = LinkMovementMethod.getInstance()
        binding.tvObesidadeDesnutricao.setLinkTextColor(resources.getColor(R.color.blue))

        binding.tvObesidade.movementMethod = LinkMovementMethod.getInstance()
        binding.tvObesidade.setLinkTextColor(resources.getColor(R.color.blue))

    }

    private fun hideLink() {
        if (result.toDouble() < 25) {
            binding.tvObesidade.visibility = View.GONE
        }
    }

    companion object {
        const val PERSON_RESULT = "personResult"
        const val DATA = "data"
    }

}