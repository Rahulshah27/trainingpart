package com.example.trainingpart

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.trainingpart.adapters.PhotosAdapter
import com.example.trainingpart.repository.MainRepository
import com.example.trainingpart.utils.RetrofitClient
import com.example.trainingpart.viewmodels.PhotosViewModel
import kotlinx.android.synthetic.main.activity_photos.*


class PhotosActivity : AppCompatActivity() {
    private val retrofitService = RetrofitClient.getInstance()
    lateinit var adapter: PhotosAdapter
    private lateinit var photoViewModelFactory: PhotosViewModel.PhotoViewModelFactory
    private val photosViewModel by lazy {
        ViewModelProvider(this, photoViewModelFactory).get(PhotosViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photos)

        photoViewModelFactory = PhotosViewModel.PhotoViewModelFactory(savedInstanceState,
            MainRepository(retrofitService)
        )

        adapter = PhotosAdapter()
        subscribeToObservers()

        rvPhotos.adapter = adapter

        val lm = GridLayoutManager(this, 6)
        lm.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return when (position % 5) {
                    0, 1 -> 3
                    else -> 2
                }
            }
        }
        rvPhotos.layoutManager = lm

    }
    private fun subscribeToObservers(){
        photosViewModel.getPhotos()
        photosViewModel.photoList.observe(this, Observer {
            if (it != null) {
                adapter.photos = it.toMutableList()
            }
        })
        photosViewModel.errorMessage.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })
    }

}