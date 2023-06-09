package com.example.diplom

import com.example.diplom.data.dataSource.SibsutisRemoteDataSource
import com.example.diplom.data.dataSource.database.AppDatabase
import com.example.diplom.data.dataSource.provideSibsutisServices
import com.example.diplom.data.remote.network.INetwork
import com.example.diplom.data.remote.network.Network
import com.example.diplom.data.remote.network.SupportInterceptor
import com.example.diplom.data.repo.NewsRepository
import com.example.diplom.data.repo.ScheduleGroupRepository
import com.example.diplom.data.repo.ScheduleRepository
import com.example.diplom.data.repo.UserRepository
import com.example.diplom.domain.repo.INewsRepository
import com.example.diplom.domain.repo.IScheduleGroupsRepository
import com.example.diplom.domain.repo.IScheduleRepository
import com.example.diplom.domain.repo.IUserRepository
import com.example.diplom.ui.login.LoginViewModel
import com.example.diplom.ui.news.NewsViewModel
import com.example.diplom.ui.news.detailednews.DetailedNewsViewModel
import com.example.diplom.ui.schedule.ScheduleViewModel
import com.example.diplom.ui.schedule.scheduleSearch.ScheduleSearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val networkModule = module {
    single { SupportInterceptor() }
    single<INetwork> { Network(get()) }
    single { provideSibsutisServices(get()) }
    single {AppDatabase.getDatabase(get())}
}

val remoteModule = module {
    single { SibsutisRemoteDataSource(get()) }
}

val repositoryModule = module {
    single<IUserRepository> { UserRepository(get()) }
    single<IScheduleRepository> {ScheduleRepository(get(),get())}
    single<INewsRepository>{NewsRepository(get(),get())}
    single<IScheduleGroupsRepository> {ScheduleGroupRepository(get(),get())}
}

val viewModelModule = module {
    viewModel { LoginViewModel(get()) }
    viewModel { ScheduleViewModel(get()) }
    viewModel { NewsViewModel(get()) }
    viewModel { DetailedNewsViewModel(get())}
    viewModel { ScheduleSearchViewModel(get())}
}

fun getModules(): List<Module> {
    return listOf(networkModule,viewModelModule, repositoryModule, remoteModule)
}