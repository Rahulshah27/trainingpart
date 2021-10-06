package com.example.trainingpart.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.trainingpart.R
import com.example.trainingpart.models.Posts
import kotlinx.android.synthetic.main.activity_post_details.view.*


class PostsAdapter(val postCallback:(Posts)->Unit) : RecyclerView.Adapter<PostsAdapter.VH>() {
    private val LAYOUT_ONE = 0
    private val LAYOUT_TWO = 1
    var posts:MutableList<Posts>?=null

        set(value) {
            field = value?.toMutableList()
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(LayoutInflater.from(parent.context).inflate(R.layout.list_item_posts, parent, false))

    }

    override fun getItemViewType(position: Int): Int {
        if (position%3==0){

            return LAYOUT_ONE
        }else{


            return LAYOUT_TWO
        }
    }
    override fun onBindViewHolder(holder: PostsAdapter.VH, position: Int) {
        holder.bind(posts?.get(position))

    }

    override fun getItemCount() = posts?.size ?: 0
    inner class VH(itemView : View) : RecyclerView.ViewHolder(itemView){

        init {
            with(itemView){
                itemView.setOnClickListener {
                    posts?.get(adapterPosition)?.let { postCallback.invoke(it) }
                }

            }
        }

        fun bind(data: Posts?) {
            //itemView.tag = data
            with(itemView){

                tvTitle.text = data?.title
                tvBody.text = data?.body



            }
        }
    }
}