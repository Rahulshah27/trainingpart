package com.example.trainingpart.repository

import com.example.trainingpart.utils.RetrofitClient

class MainRepository constructor(private val retrofitClient: RetrofitClient) {
    fun getAllPhotos() = retrofitClient.getPhotos()
    fun getAllPosts() = retrofitClient.getPosts()
    fun getPostDetails(postId:Int) = retrofitClient.getPostDetail(postId)
    fun getComments(postId: Int) = retrofitClient.getComments(postId)
}