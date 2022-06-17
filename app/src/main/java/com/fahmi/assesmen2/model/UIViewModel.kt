package com.fahmi.assesmen2.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.fahmi.assesmen2.UIModePreference
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UIViewModel(application: Application):
    AndroidViewModel(application){


    private val uiDataStore = UIModePreference(application)

    // 1
    val getUIMode = uiDataStore.uiMode

    // 2
    fun saveToDataStore(isNightMode: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            uiDataStore.saveToDataStore(isNightMode)
        }
    }
}