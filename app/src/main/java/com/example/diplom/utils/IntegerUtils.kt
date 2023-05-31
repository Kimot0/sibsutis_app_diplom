package com.example.diplom.utils

fun Int.convertNumberToDayOfWeek(): String? {
    return when (this) {
        0 -> "ПН"
        1 -> "ВТ"
        2 -> "СР"
        3 -> "ЧТ"
        4 -> "ПТ"
        5 -> "СБ"
        else -> null
    }
}