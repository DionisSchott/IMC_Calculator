package com.dionis.imc

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.dionis.imc.ResultFragment.Companion.DATA
import com.dionis.imc.ResultFragment.Companion.PERSON_RESULT
import com.dionis.imc.databinding.FragmentHomeBinding
import com.dionis.imc.ui.MainViewModel
import java.text.SimpleDateFormat
import java.util.*


class HomeFragment : Fragment() {


    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: MainViewModel

    private var result: Float = 0.0F


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        setUp()
    }


    private fun setUp() {
        setUpClicks()
    }


    private fun setUpClicks() {

        binding.btCalcular.setOnClickListener {
            validateFields()
        }

        binding.btnResult.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_dataFragment)
        }
    }

    private fun setUpObserver() {

        viewModel.personList.observe(viewLifecycleOwner, Observer {


            val item = it.size - 1

            val args = Bundle().apply { putFloat(PERSON_RESULT, result); putSerializable(DATA, it[item]) }
            findNavController().navigate(R.id.action_homeFragment_to_resultFragment, args)


        })
    }


    private fun validateFields(): Boolean {

        var error = false

        if (binding.etAltura.text.isEmpty()) {
            binding.etAltura.error = "digite sua altura"
            Toast.makeText(context, "NÃO FOI POSSÍVEL CALCULAR", Toast.LENGTH_SHORT).show()
            error = true
        }; if (binding.etPeso.text.isEmpty()) {
            binding.etPeso.error = "digite seu peso"
            Toast.makeText(context, "NÃO FOI POSSÍVEL CALCULAR", Toast.LENGTH_SHORT).show()
            error = true

        } else {
            calculateImc()
            saveData()
            setUpObserver()
        }

        return error
    }


    private fun saveData() {
        if (binding.etNome.text.isNotEmpty()) {
            val date = Calendar.getInstance().time
            val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

            val nome = binding.etNome.text.toString()
            val peso = binding.etPeso.text.toString().toFloat()
            val altura = binding.etAltura.text.toString().toFloat()
            val resultado = result.toString().toFloat()
            val data = dateFormat.format(date)

            viewModel.save(nome, altura, peso, resultado, data)
        }
    }

    private fun calculateImc() {
        val peso = binding.etPeso.text.toString().toFloat()
        val alt = binding.etAltura.text.toString().toFloat()
        val resultado = peso / (alt * alt)

        result = String.format("%.2f", resultado).toFloat()
    }


}