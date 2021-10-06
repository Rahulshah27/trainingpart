package com.example.trainingpart.viewmodels

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.trainingpart.models.Photos
import com.example.trainingpart.repository.MainRepository

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



class PhotosViewModel constructor(private val bundle: Bundle?, private val mainRepository: MainRepository):
    ViewModel() {
    val photoList = MutableLiveData<List<Photos>>()
    val errorMessage = MutableLiveData<String>()

    fun getPhotos(){
        val response = mainRepository.getAllPhotos()
        response.enqueue(object : Callback<List<Photos>> {
            override fun onResponse(call: Call<List<Photos>>, response: Response<List<Photos>>) {
                photoList.postValue(response.body())
            }

            override fun onFailure(call: Call<List<Photos>>, t: Throwable) {
                errorMessage.postValue(t.message)
            }

        })
    }
    class PhotoViewModelFactory constructor(private val bundle: Bundle?,private val mainRepository: MainRepository): ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return if (modelClass.isAssignableFrom(PhotosViewModel::class.java)){
                PhotosViewModel(this.bundle,this.mainRepository) as T
            }else{
                throw IllegalArgumentException("ViewModel not found")
            }
        }

    }
}