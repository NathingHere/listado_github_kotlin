package com.example.listado_github_kotlin.ui.Koin

import com.example.listado_github_kotlin.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel {
        MainViewModel(get(), get())
    }

}