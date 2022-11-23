package com.dionis.imc.di

import com.dionis.imc.database.PersonDataBase
import com.dionis.imc.repository.PersonDataRepository
import com.dionis.imc.ui.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module



val viewModelModule = module {
    viewModel { MainViewModel(get()) }
}

val repositoryModule = module {
    single { PersonDataRepository(get()) }
}

val daoModule = module {
    single { PersonDataBase.getInstance(androidContext()).personDataDao() }
}
