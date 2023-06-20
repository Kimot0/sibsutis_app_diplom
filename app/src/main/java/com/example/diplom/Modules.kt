package com.example.diplom

import com.example.diplom.data.dataSource.SibsutisRemoteDataSource
import com.example.diplom.data.dataSource.database.AppDatabase
import com.example.diplom.data.dataSource.provideSibsutisServices
import com.example.diplom.data.remote.network.INetwork
import com.example.diplom.data.remote.network.Network
import com.example.diplom.data.remote.network.SupportInterceptor
import com.example.diplom.data.repo.*
import com.example.diplom.domain.repo.*
import com.example.diplom.ui.attendance.AttendanceViewModel
import com.example.diplom.ui.attendanceTeacher.AttendanceTeacherViewModel
import com.example.diplom.ui.attendanceTeacher.detailedteacherattendance.DetailedTeacherAttendanceViewModel
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
    single { AppDatabase.getDatabase(get()).getDisciplinesDao() }
}

val remoteModule = module {
    single { SibsutisRemoteDataSource(get()) }
}

val repositoryModule = module {

    single<IDisciplineRepo> {
        DisciplinesRepository(
            source = get(),
            disciplinesDao = get()
        )
    }

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
            groupRepository = get(),
            disciplinesDao = get()
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

    single<IHeadGroupListSendRepository> {
        HeadGroupListSendRepository(
            get()
        )
    }

    single<IGetHeadListRepo> {
        GetHeadListRepo(
            get(),
            get()
        )
    }
}

val viewModelModule = module {
    viewModel {
        LoginViewModel(
            userRepository = get(),
            disciplinesRepository = get()
        )
    }
    viewModel { ScheduleViewModel(get()) }
    viewModel { NewsViewModel(get()) }
    viewModel { DetailedNewsViewModel(get()) }

    viewModel {
        AttendanceViewModel(
            groupRepository = get(),
            disciplinesRepository = get(),
            get()
        )
    }

    viewModel {
        AttendanceTeacherViewModel(
            get()
        )
    }

    viewModel {
        DetailedTeacherAttendanceViewModel(
            get()
        )
    }
}

fun getModules(): List<Module> {
    return listOf(networkModule, viewModelModule, repositoryModule, remoteModule)
}