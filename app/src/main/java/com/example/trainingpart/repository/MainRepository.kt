package com.example.trainingpart.repository

import com.example.trainingpart.models.Comments
import com.example.trainingpart.models.Photos
import com.example.trainingpart.models.Posts
import com.example.trainingpart.utils.RetrofitClient
import retrofit2.Call

class MainRepository constructor(private val retrofitClient: RetrofitClient):IMainRepository {
    override fun getPhotos(): Call<List<Photos>> = retrofitClient.getPhotos()

    override fun getPosts(): Call<List<Posts>> = retrofitClient.getPosts()

    override fun getPostDetail(postId: Int): Call<Posts> = retrofitClient.getPostDetail(postId)

    override fun getComments(postId: Int): Call<List<Comments>> = retrofitClient.getComments(postId)
}