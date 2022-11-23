package com.dionis.imc.repository

import androidx.lifecycle.LiveData
import com.dionis.imc.dao.PersonDataDao
import com.dionis.imc.model.PersonData

class PersonDataRepository(private val personDataDao: PersonDataDao) {

    val personData: LiveData<List<PersonData>>
        get() = personDataDao.getAll()


    suspend fun save(Person: String, altura: Float, peso: Float, resultado: Float) {
        personDataDao.save(PersonData(nome = Person, altura = altura, peso = peso, resultado = resultado))
    }

    suspend fun update(person: PersonData) {
        personDataDao.update(person)
    }

    suspend fun delete(person: PersonData) {
        personDataDao.remove(person)
    }

    fun get(person: PersonData): PersonData =
        personDataDao.get(person.id)



}