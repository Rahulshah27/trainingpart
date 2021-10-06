package com.example.trainingpart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.trainingpart.adapters.CommentsAdapter
import com.example.trainingpart.repository.MainRepository
import com.example.trainingpart.utils.Constants
import com.example.trainingpart.utils.RetrofitClient
import com.example.trainingpart.viewmodels.PostsViewModel
import kotlinx.android.synthetic.main.activity_post_details.*


class PostDetailsActivity : AppCompatActivity() {
    private val retrofitService = RetrofitClient.getInstance()
    lateinit var adapter: CommentsAdapter
    private lateinit var postsViewModelFactor: PostsViewModel.PostViewModelFactory
    private val postsViewModel by lazy {
        ViewModelProvider(this, postsViewModelFactor).get(PostsViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_details)

        postsViewModelFactor = PostsViewModel.PostViewModelFactory(savedInstanceState,
            MainRepository(retrofitService)
        )

        adapter = CommentsAdapter()
        subscribeToObservers()
        rvDetails.adapter = adapter
        rvDetails.apply {
            layoutManager = LinearLayoutManager(this@PostDetailsActivity, RecyclerView.VERTICAL,false)
            addItemDecoration(DividerItemDecoration(this@PostDetailsActivity, RecyclerView.VERTICAL))
        }
    }

    private fun subscribeToObservers(){
        Constants.Num.postId?.let { postsViewModel.getPostDetails(it) }

        Constants.Num.postId?.let { postsViewModel.getCommentList(it) }

        postsViewModel.postDetail.observe(this, Observer {
            if (it != null) {
                tvTitle.text = it.title
                tvBody.text = it.body
            }
        })

        postsViewModel.errorMessage.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })

        postsViewModel.commentList.observe(this, Observer {
            if (it != null){
                adapter.comments = it.toMutableList()
            }
        })

    }
}