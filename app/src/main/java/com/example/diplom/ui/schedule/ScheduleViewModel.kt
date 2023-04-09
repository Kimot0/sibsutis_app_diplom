package com.example.diplom.ui.schedule

import androidx.lifecycle.ViewModel
import com.example.diplom.domain.entity.Lesson

class ScheduleViewModel : ViewModel(){
    val tabTitles = arrayOf(
        "ПН", "ВТ", "СР", "ЧТ", "ПТ", "СБ"
    )
    val scheduleArray = listOf(
        listOf(
            Lesson(
                "08:00", "Агалаков А.А.", "432", "Практика","СТП2","both"
            ),
            Lesson(
                "09:50", "Мачикина", "218", "Лекция","ТИ","both"
            )
        ),
        listOf(
            Lesson(
                "08:00", "Перцев И.В.", "432", "Практика","ПГИ","both"
            ),
            Lesson(
                "09:50", "Перцев И.В.", "210", "Лекция","ПГИ","both"
            ),
            Lesson(
                "11:40", "Дьячкова И.С.", "218", "Лекция","СБД","both"
            ),
            Lesson(
                "13:45", "Дементьева К.И.", "631", "Практика","ТИ","both"
            ),
        ),
        listOf(
            Lesson(
                "08:00", "Дементьева К.И.", "631", "Практика","ТИ","both"
            ),
            Lesson(
                "09:50", "Дьячкова М.С.", "631", "Практика","СБД","both"
            )
        ),
        listOf(),
        listOf(),
        listOf()
    )
}