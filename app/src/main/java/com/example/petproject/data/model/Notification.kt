package com.example.petproject.data.model

import kotlinx.serialization.Serializable


@Serializable
class Notification(var hour: Int = 20, var minute: Int = 10) {
    var year: Int? = 2020
    var month: Int? = 15
    var day: Int? = 25

    fun dateString(): String {
        return if (month != null) {
            String.format(
                "${year.toString()}." +
                        "$month.toString()}." +
                        day.toString()
            )
        } else {
            "2020.10.10"
        }
    }

    fun timeString(): String {
        return String.format(
            "$hour" +
                    minute.toString()
        )
    }
}