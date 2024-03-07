package com.repasoAndroid.androidmaster.settings

//PAra poder devolver todas los settings guardados en getSettings
// Es necesario crear un objeto con toda la información porque sino solo permite devolver 1 campo a la vez
data class SettingsModel(
    var volume: Int,
    var bluetooth: Boolean,
    var darkMode: Boolean,
    var vibration: Boolean
)