package com.example.diplom

import com.example.diplom.data.dataSource.SibsutisRemoteDataSource
import com.example.diplom.data.dataSource.provideSibsutisServices
import com.example.diplom.data.remote.network.INetwork
import com.example.diplom.data.remote.network.Network
import com.example.diplom.data.remote.network.SupportInterceptor
import com.example.diplom.data.repo.UserRepo
import com.example.diplom.domain.repo.IUserRepo
import com.example.diplom.ui.login.LoginViewModel
import com.example.diplom.ui.news.NewsViewModel
import com.example.diplom.ui.news.detailednews.DetailedNewsViewModel
import com.example.diplom.ui.schedule.ScheduleViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val networkModule = module {
    single { SupportInterceptor() }
    single<INetwork> { Network(get()) }
    single { provideSibsutisServices(get()) }
}

val remoteModule = module {
    single { SibsutisRemoteDataSource(get()) }
}

val repositoryModule = module {
    single<IUserRepo> { UserRepo(get()) }
}

val viewModelModules = module {
    viewModel { LoginViewModel(get()) }
    viewModel { ScheduleViewModel() }
    viewModel { NewsViewModel() }
}

fun getModules(): List<Module> {
    return listOf(networkModule,viewModelModules, repositoryModule, remoteModule)
}