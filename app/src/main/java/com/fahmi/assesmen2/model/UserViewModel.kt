package com.fahmi.assesmen2.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.fahmi.assesmen2.db.MahasiswaDatabase
import com.fahmi.assesmen2.db.Mahasiswa
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {

    val readAllData: LiveData<List<Mahasiswa>>
    private val repository: MahasiswaRepository

    init {
        val userDao = MahasiswaDatabase.getDatabase(application).userDao()
        repository = MahasiswaRepository(userDao)
        readAllData = repository.readAllData
    }

    fun addUser(mahasiswa: Mahasiswa) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(mahasiswa)
        }
    }
    fun updateMahasiswa(mahasiswa: Mahasiswa) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateMahasiswa(mahasiswa)
        }
    }
    fun deleteMahasiswa(mahasiswa: Mahasiswa){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteMahasiswa(mahasiswa)
        }
    }
    fun deleteALlMahasiswa(){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteAllMahasiswa()
        }
    }
}