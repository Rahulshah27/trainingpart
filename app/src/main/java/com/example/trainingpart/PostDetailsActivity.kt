package com.example.trainingpart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.trainingpart.repository.Result
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.trainingpart.adapters.CommentsAdapter
import com.example.trainingpart.utils.Constants
import com.example.trainingpart.viewmodelfactory.ViewModelFactory
import com.example.trainingpart.viewmodels.PostsViewModel
import kotlinx.android.synthetic.main.activity_post_details.*


class PostDetailsActivity : AppCompatActivity() {

    lateinit var adapter: CommentsAdapter
//    private lateinit var postsViewModelFactor: PostsViewModel.PostViewModelFactory
    private val postsViewModel by viewModels<PostsViewModel> { ViewModelFactory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_details)

//        postsViewModelFactor = PostsViewModel.PostViewModelFactory(savedInstanceState,
//            MainRepository(retrofitService)
//        )

        adapter = CommentsAdapter()
        rvDetails.adapter = adapter
        rvDetails.apply {
            layoutManager = LinearLayoutManager(this@PostDetailsActivity, RecyclerView.VERTICAL,false)
            addItemDecoration(DividerItemDecoration(this@PostDetailsActivity, RecyclerView.VERTICAL))
        }

        Constants.Num.postId?.let { postsViewModel.getPostDetails(it) }
        Constants.Num.postId?.let { postsViewModel.getCommentList(it) }
        subscribeToObservers()
    }

    private fun subscribeToObservers(){
        postsViewModel.postDetail.observe(this, {
            when(it){
                is Result.Success->{
                    tvTitle.text = it.value.body()?.title
                    tvBody.text = it.value.body()?.body
                }
                is Result.Failure->{Toast.makeText(this, it.message,Toast.LENGTH_SHORT).show()}
            }

        })

        postsViewModel.commentList.observe(this,  {
            when(it){
                is Result.Success->{
                    adapter.comments = it.value.body()?.toMutableList()
                }
                is Result.Failure->{Toast.makeText(this, it.message,Toast.LENGTH_SHORT).show()}
            }
        })
    }
}