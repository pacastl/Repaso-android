package com.repasoAndroid.androidmaster.settings

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import com.repasoAndroid.androidmaster.databinding.ActivitySettingsBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore

//Función de extensión para estar dentro del contexto de Android
//El nombre de la base de datos es settings, y lo del by es para crear 1 sola instancia siguiendo el patrón Singleton
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class SettingsActivity : AppCompatActivity() {

    //    Las cte en los companion object
    companion object {
        const val VOLUME_LVL = "volume_lvl"
        const val KEY_BLUETOOTH = "key_bluetooth"
        const val KEY_VIBRATION = "key_vibration"
        const val KEY_DARK_MODE = "key_dark_mode"
    }

    private lateinit var binding: ActivitySettingsBinding
//    La necestiamos porque se vuelve loco al guardar los valores debido a que el float
//    está siempre atento a los cambios y guarda constantemente los cualquier cambio
    private var firstTime:Boolean=true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        REcuperamos los valores
        CoroutineScope(Dispatchers.IO).launch {
//            filtramos por firstTime par que nose vuelva loco y guarde cualquier cambio
            getSettings().filter{firstTime}.collect{ settingsModel ->

                if(settingsModel !=null){
//                    Estamos modificando la interfaz y NO se puede desde corrutinas
                    runOnUiThread{
                        binding.switchVibration.isChecked=settingsModel.vibration
                        binding.switchBluetooth.isChecked=settingsModel.bluetooth
                        binding.switchDarkMode.isChecked=settingsModel.darkMode
                        binding.rsVolume.setValues(settingsModel.volume.toFloat())
                        firstTime=!firstTime
                    }

                }
            }
        }

        initUi()
    }

    private fun initUi() {
        binding.rsVolume.addOnChangeListener { _, value, _ ->
            Log.i("Aris", "El valor es ${value}")

//            Hay que usar una corrutia para guardar el valor: IO es para persistencias a datos, llamadas
//            a backend....
            CoroutineScope(Dispatchers.IO).launch {
                saveVolume(value.toInt())
            }
        }

        binding.switchBluetooth.setOnCheckedChangeListener { _, value ->
            CoroutineScope(Dispatchers.IO).launch() {
//                Hay que poner distintas keys porque sino compartirían las misma y se sobrescribirían
                saveOptions(KEY_BLUETOOTH, value)
            }

        }
        binding.switchVibration.setOnCheckedChangeListener { _, value ->
            CoroutineScope(Dispatchers.IO).launch() {
//                Hay que poner distintas keys porque sino compartirían las misma y se sobrescribirían
                saveOptions(KEY_VIBRATION, value)
            }

        }

        binding.switchDarkMode.setOnCheckedChangeListener { _, value ->

            if(value){
                enableDarkMode()
            }else{
                disableDarkMode()
            }
            CoroutineScope(Dispatchers.IO).launch() {
//                Hay que poner distintas keys porque sino compartirían las misma y se sobrescribirían
                saveOptions(KEY_DARK_MODE, value)
            }

        }
    }

    //    Para guardar datos
    private suspend fun saveVolume(value: Int) {
//      Edita la base de datos. Para guardar, clave-valor y para ello necesitamos una constante
        dataStore.edit { preferences ->
//            Como solo hay 1 range slider, no necesitamos clave-valor sino el valor solo
            preferences[intPreferencesKey(VOLUME_LVL)] = value
        }
    }

    private suspend fun saveOptions(key: String, value: Boolean) {
        dataStore.edit { preferences ->
            preferences[booleanPreferencesKey(key)] = value
        }
    }

//    Devuelve todos los valores guardados
    private fun getSettings(): Flow<SettingsModel> {
       return dataStore.data.map { preferences->
           SettingsModel(
//               Si no tiene valor, devuelve 50 como valor por defecto
               volume = preferences[intPreferencesKey(VOLUME_LVL)] ?:50,
               bluetooth = preferences[booleanPreferencesKey(KEY_BLUETOOTH)]?:true,
               darkMode = preferences[booleanPreferencesKey(KEY_DARK_MODE)]?:false,
               vibration = preferences[booleanPreferencesKey(KEY_VIBRATION)]?:true
           )

        }
    }

//    Pone el modo oscuro
    private fun enableDarkMode(){
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        delegate.applyDayNight()
    }
//    Quita el modo oscuro
    private fun disableDarkMode(){
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        delegate.applyDayNight()
    }
}

