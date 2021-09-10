package com.azhar.pixeleditor.DependencyInjection

import com.azhar.pixeleditor.Model.Repository.EditImageRepository
import com.azhar.pixeleditor.Model.Repository.EditImageRepositoryImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val repositoryModule = module {
    factory <EditImageRepository> { EditImageRepositoryImpl(androidContext())  }
}