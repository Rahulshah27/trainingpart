package com.example.trainingpart.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.trainingpart.repository.Result
import com.example.trainingpart.models.Photos
import com.example.trainingpart.repository.IMainRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PhotosViewModel constructor(private val iMainRepository: IMainRepository): ViewModel() {

        val obResult = MutableLiveData<Result<Response<List<Photos>>>>()

        fun getPhotos(){
        val response = iMainRepository.getPhotos()
        response.enqueue(object : Callback<List<Photos>> {
            override fun onResponse(call: Call<List<Photos>>, response: Response<List<Photos>>) {
                obResult.value = Result.Success(response)
            }

            override fun onFailure(call: Call<List<Photos>>, t: Throwable) {
                obResult.value = Result.Failure(t.message)
            }

        })
    }
}