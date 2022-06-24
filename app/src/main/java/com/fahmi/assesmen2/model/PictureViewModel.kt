/*
 * Copyright (C) 2021 The Android Open Source Project.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.fahmi.assesmen2.fragments.picture

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fahmi.assesmen2.network.PhotoApi
import com.fahmi.assesmen2.db.Picture
import kotlinx.coroutines.launch
import java.lang.Exception

enum class PictureApiStatus{ LOADING, ERROR, DONE}

class PictureViewModel : ViewModel() {

    private val _status = MutableLiveData<PictureApiStatus>()
    private val _photos = MutableLiveData<List<Picture>>()

    val status: LiveData<PictureApiStatus> = _status
    val photos: LiveData<List<Picture>> = _photos

    init {
        getPhoto()
    }
    private fun getPhoto() {
        viewModelScope.launch {
            _status.value = PictureApiStatus.LOADING
            try {
                _photos.value = PhotoApi.retrofitService.getPhotos()
                _status.value = PictureApiStatus.DONE
            }catch (e: Exception){
                _status.value = PictureApiStatus.ERROR
                _photos.value = listOf()
            }

        }
    }
}
