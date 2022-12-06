package com.dionis.imc.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.dionis.imc.database.PersonDataBase
import com.dionis.imc.model.PersonData
import com.dionis.imc.repository.PersonDataRepository
import kotlinx.coroutines.launch


class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: PersonDataRepository

    val _readAllData: LiveData<List<PersonData>>
    val personList: LiveData<List<PersonData>> get() = _readAllData


    init {
        val personDao = PersonDataBase.getInstance(application).personDataDao()
        repository = PersonDataRepository(personDao)
        _readAllData = repository.personData
    }


    fun save(nome: String, altura: Float, peso: Float, resultado: Float, data: String) {

        viewModelScope.launch {
            repository.save(nome, altura, peso, resultado, data)
        }
    }

    fun delete(user: PersonData) {
        viewModelScope.launch {
            repository.delete(user)
        }
    }


}