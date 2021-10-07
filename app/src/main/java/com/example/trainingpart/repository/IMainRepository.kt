package com.example.trainingpart.repository

import com.example.trainingpart.models.Comments
import com.example.trainingpart.models.Photos
import com.example.trainingpart.models.Posts
import retrofit2.Call

interface IMainRepository {
    fun getPhotos():Call<List<Photos>>
    fun getPosts():Call<List<Posts>>
    fun getPostDetail(postId:Int):Call<Posts>
    fun getComments(postId: Int):Call<List<Comments>>
}