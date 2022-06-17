package com.fahmi.assesmen2.model

import androidx.lifecycle.LiveData
import com.fahmi.assesmen2.db.Mahasiswa
import com.fahmi.assesmen2.db.MahasiswaDao

class MahasiswaRepository(private val mahasiswaDao: MahasiswaDao) {

    val readAllData: LiveData<List<Mahasiswa>> = mahasiswaDao.readAllData()

    suspend fun addUser(mahasiswa: Mahasiswa){
        mahasiswaDao.addUser(mahasiswa)
    }
    suspend fun updateMahasiswa(mahasiswa: Mahasiswa){
        mahasiswaDao.updateMahasiswa(mahasiswa)
    }

    suspend fun deleteMahasiswa(mahasiswa: Mahasiswa){
        mahasiswaDao.deleteMahasiswa(mahasiswa)
    }
    suspend fun deleteAllMahasiswa(){
        mahasiswaDao.deleteALlMahasiswa()
    }

}