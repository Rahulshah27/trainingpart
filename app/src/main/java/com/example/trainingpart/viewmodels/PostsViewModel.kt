package com.example.trainingpart.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.trainingpart.models.Comments
import com.example.trainingpart.models.Posts
import com.example.trainingpart.repository.IMainRepository
import com.example.trainingpart.repository.Result
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostsViewModel  constructor(private val iMainRepo: IMainRepository) :ViewModel(){
    val postsList =  MutableLiveData<Result<Response<List<Posts>>>>()
    val postDetail =  MutableLiveData<Result<Response<Posts>>>()
    val commentList =  MutableLiveData<Result<Response<List<Comments>>>>()

    fun getPosts(){
        val response = iMainRepo.getPosts()
        response.enqueue(object : Callback<List<Posts>> {
            override fun onResponse(call: Call<List<Posts>>, response: Response<List<Posts>>) {
                postsList.value = Result.Success(response)
            }

            override fun onFailure(call: Call<List<Posts>>, t: Throwable) {
                postsList.value = Result.Failure(t.message)
            }

        })
    }
    fun getPostDetails(postId:Int){
        val postDetailResponse = iMainRepo.getPostDetail(postId)
        postDetailResponse.enqueue(object : Callback<Posts>{
            override fun onResponse(call: Call<Posts>, response: Response<Posts>) {
                postDetail.value = Result.Success(response)
            }

            override fun onFailure(call: Call<Posts>, t: Throwable) {
                postDetail.value = Result.Failure(t.message)
            }

        })
    }

    fun getCommentList(postId:Int){
        val getCommentsResponse = iMainRepo.getComments(postId)
        getCommentsResponse.enqueue(object : Callback<List<Comments>> {
            override fun onResponse(call: Call<List<Comments>>, response: Response<List<Comments>>) {
                commentList.value = Result.Success(response)
            }

            override fun onFailure(call: Call<List<Comments>>, t: Throwable) {
                commentList.value = Result.Failure(t.message)
            }

        })
    }



}