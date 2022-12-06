package com.dionis.imc

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.dionis.imc.databinding.FragmentDataBinding
import com.dionis.imc.ui.MainViewModel
import com.dionis.imc.ui.PersonAdapter


class DataFragment : Fragment() {

    private lateinit var binding: FragmentDataBinding
    private lateinit var personAdapter: PersonAdapter
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentDataBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
    }

    private fun setup() {
        setupAdapters()
    }

    private fun setupAdapters() {
        personAdapter = PersonAdapter()

        binding.rvPerson.adapter = personAdapter
        binding.rvPerson.layoutManager = LinearLayoutManager(context)


        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel._readAllData.observe(viewLifecycleOwner, Observer { person ->
            personAdapter.updateList(person)
        })

        viewModel.personList.observe(viewLifecycleOwner, Observer {
            if(it.isEmpty()) {binding.tvDataEmpty.visibility = View.VISIBLE}
        })

        personAdapter.onItemClicked = {
            val args = Bundle().apply { putFloat(ResultFragment.PERSON_RESULT, it.resultado); putSerializable(ResultFragment.DATA, it) }
            findNavController().navigate(R.id.action_dataFragment_to_resultFragment, args)
        }

        personAdapter.onLongItemClicked = {
            viewModel.delete(it)
        }
    }



}