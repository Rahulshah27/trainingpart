package com.example.trainingpart

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.trainingpart.repository.Result
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.trainingpart.adapters.PostsAdapter
import com.example.trainingpart.models.Posts
import com.example.trainingpart.utils.Constants
import com.example.trainingpart.viewmodelfactory.ViewModelFactory
import com.example.trainingpart.viewmodels.PostsViewModel
import kotlinx.android.synthetic.main.activity_posts.*


class PostsActivity : AppCompatActivity() {
    lateinit var adapter: PostsAdapter

//    private val retrofitService = RetrofitClient.getInstance()
//    private lateinit var postsViewModelFactor: PostsViewModel.PostViewModelFactory
//    private val postsViewModel by lazy {
//        ViewModelProvider(this, postsViewModelFactor).get(PostsViewModel::class.java)
//    }

    private val postsViewModel by viewModels<PostsViewModel> { ViewModelFactory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_posts)

//        postsViewModelFactor = PostsViewModel.PostViewModelFactory(
//            savedInstanceState,
//            MainRepository(retrofitService)
//        )
        postsViewModel.getPosts()
        subscribeToObservers()
        adapter = PostsAdapter(::itemClicked)


        rvPosts.adapter = adapter
        rvPosts.setHasFixedSize(true)
        rvPosts.apply {
            layoutManager = LinearLayoutManager(this@PostsActivity, RecyclerView.VERTICAL, false)
        }
    }

    private fun itemClicked(posts: Posts) {
        Constants.Num.postId = posts.id
        startActivity((Intent(this, PostDetailsActivity::class.java)))
    }

    private fun subscribeToObservers() {
        postsViewModel.postsList.observe(this, {
            when(it){
                is Result.Success->{ adapter.posts = it.value.body()?.toMutableList() }
                is Result.Failure->{Toast.makeText(this, it.message,Toast.LENGTH_SHORT).show()}
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.post_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.action_posts ->{
                Toast.makeText(this, "choose other option!", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.action_photos -> {
                val intent = Intent(this, PhotosActivity::class.java)
                startActivity(intent)
                true
            }
            else-> super.onOptionsItemSelected(item)
        }
    }
}