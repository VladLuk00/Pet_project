package com.example.petproject.data.model

import kotlinx.serialization.Serializable


@Serializable
class Notification(var hour: Int = 0, var minute: Int = 0) {
    var year: Int = 0
    var month: Int = 0
    var day: Int = 0
}