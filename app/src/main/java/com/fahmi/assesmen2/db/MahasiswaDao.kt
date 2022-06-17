package com.fahmi.assesmen2.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface MahasiswaDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(mahasiswa: Mahasiswa)

    @Update
    suspend fun updateMahasiswa(mahasiswa: Mahasiswa)

    @Delete
    suspend fun deleteMahasiswa(mahasiswa: Mahasiswa)

    @Query("DELETE FROM tabel_mahasiswa")
    suspend fun deleteALlMahasiswa()

    @Query("SELECT * FROM tabel_mahasiswa ORDER BY id ASC")
    fun readAllData(): LiveData<List<Mahasiswa>>

}