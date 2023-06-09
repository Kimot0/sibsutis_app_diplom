package com.example.diplom

import com.example.diplom.data.dataSource.SibsutisRemoteDataSource
import com.example.diplom.data.dataSource.database.AppDatabase
import com.example.diplom.data.dataSource.provideSibsutisServices
import com.example.diplom.data.remote.network.INetwork
import com.example.diplom.data.remote.network.Network
import com.example.diplom.data.remote.network.SupportInterceptor
import com.example.diplom.data.repo.GroupRepository
import com.example.diplom.data.repo.NewsRepo
import com.example.diplom.data.repo.ScheduleRepo
import com.example.diplom.data.repo.UserDbRepository
import com.example.diplom.data.repo.UserRepository
import com.example.diplom.domain.repo.IGroupRepo
import com.example.diplom.domain.repo.INewsRepo
import com.example.diplom.domain.repo.IScheduleRepo
import com.example.diplom.domain.repo.IUserDbRepo
import com.example.diplom.domain.repo.IUserRepo
import com.example.diplom.ui.attendance.AttendanceViewModel
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
    single { AppDatabase.getDatabase(get()) }
    single { AppDatabase.getDatabase(get()).getUserDao() }
    single { AppDatabase.getDatabase(get()).getGroupDao() }
}

val remoteModule = module {
    single { SibsutisRemoteDataSource(get()) }
}

val repositoryModule = module {

    single<IGroupRepo> {
        GroupRepository(
            groupDao = get()
        )
    }

    single<IUserDbRepo> {
        UserDbRepository(
            userDao = get()
        )
    }

    single<IUserRepo> {
        UserRepository(
            source = get(),
            userDbRepository = get(),
            groupRepository = get()
        )
    }

    single<IScheduleRepo> {
        ScheduleRepo(
            source = get()
        )
    }

    single<INewsRepo> {
        NewsRepo(
            source = get(),
            database = get()
        )
    }
}

val viewModelModule = module {
    viewModel { LoginViewModel(get()) }
    viewModel { ScheduleViewModel(get()) }
    viewModel { NewsViewModel(get()) }
    viewModel { DetailedNewsViewModel(get()) }

    viewModel {
        AttendanceViewModel(
            groupRepository = get()
        )
    }
}

fun getModules(): List<Module> {
    return listOf(networkModule, viewModelModule, repositoryModule, remoteModule)
}