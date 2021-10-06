package com.example.trainingpart

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.trainingpart.adapters.PostsAdapter
import com.example.trainingpart.models.Posts
import com.example.trainingpart.repository.MainRepository
import com.example.trainingpart.utils.Constants
import com.example.trainingpart.utils.RetrofitClient
import com.example.trainingpart.viewmodels.PostsViewModel
import kotlinx.android.synthetic.main.activity_posts.*


class PostsActivity : AppCompatActivity() {

    private val retrofitService = RetrofitClient.getInstance()
    lateinit var adapter: PostsAdapter
    private lateinit var postsViewModelFactor: PostsViewModel.PostViewModelFactory
    private val postsViewModel by lazy {
        ViewModelProvider(this, postsViewModelFactor).get(PostsViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_posts)

        postsViewModelFactor = PostsViewModel.PostViewModelFactory(
            savedInstanceState,
            MainRepository(retrofitService)
        )
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
        postsViewModel.getPosts()
        postsViewModel.postList.observe(this, Observer {
            if (it != null) {
                adapter.posts = it.toMutableList()

            }

        })
        postsViewModel.errorMessage.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
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