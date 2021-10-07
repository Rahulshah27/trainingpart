package com.example.trainingpart

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.trainingpart.adapters.PhotosAdapter
import com.example.trainingpart.repository.Result
import com.example.trainingpart.viewmodelfactory.ViewModelFactory
import com.example.trainingpart.viewmodels.PhotosViewModel
import kotlinx.android.synthetic.main.activity_photos.*


class PhotosActivity : AppCompatActivity() {

    lateinit var adapter: PhotosAdapter
//    private lateinit var photoViewModelFactory: PhotosViewModel.PhotoViewModelFactory
//    private val photosViewModel by lazy {
//        ViewModelProvider(this, photoViewModelFactory).get(PhotosViewModel::class.java)
//    }

    private val photosViewModel by viewModels<PhotosViewModel>{ViewModelFactory()}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photos)

        photosViewModel.getPhotos()
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

//        photoViewModelFactory = PhotosViewModel.PhotoViewModelFactory(savedInstanceState,
//            MainRepository(retrofitService)
//        )


    }
    private fun subscribeToObservers(){
        photosViewModel.obResult.observe(this, {
            when(it){
                is Result.Success -> { adapter.photos = it.value.body()?.toMutableList() }
                is Result.Failure -> { Toast.makeText(this, it.message, Toast.LENGTH_LONG).show() }
            }
        })
    }

}