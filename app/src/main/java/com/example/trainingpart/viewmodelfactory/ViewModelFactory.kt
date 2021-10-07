package com.example.trainingpart.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.trainingpart.repository.IMainRepository
import com.example.trainingpart.repository.MainRepository
import com.example.trainingpart.utils.RetrofitClient

class ViewModelFactory: ViewModelProvider.Factory {

    init {
        getInstance()
    }
    companion object {
        @Volatile
        private var INSTANCE: IMainRepository?=null
    }

    fun getInstance() =
        INSTANCE ?: synchronized(ViewModelFactory::class.java){
            INSTANCE?: MainRepository(
                RetrofitClient.getInstance()
            ).also { INSTANCE = it }

            fun destroyInstance(){
                INSTANCE = null
            }
        }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(IMainRepository::class.java).newInstance(INSTANCE)
    }
}