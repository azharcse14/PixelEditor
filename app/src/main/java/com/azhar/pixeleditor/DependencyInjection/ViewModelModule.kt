package com.azhar.pixeleditor.DependencyInjection

import com.azhar.pixeleditor.ViewModel.EditImageViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { EditImageViewModel(editImageRepository = get()) }
}