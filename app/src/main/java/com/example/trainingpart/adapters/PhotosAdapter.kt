package com.example.trainingpart.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.trainingpart.R
import com.example.trainingpart.models.Photos
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item_photo.view.*


class PhotosAdapter: RecyclerView.Adapter<PhotosAdapter.VH>() {
    private val LAYOUT_ONE = 0
    private val LAYOUT_TWO = 1

    var photos:MutableList<Photos>?=null
        set(value) {
            field = value?.toMutableList()
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotosAdapter.VH {
        return VH(LayoutInflater.from(parent.context).inflate(R.layout.list_item_photo, parent, false))
    }

    override fun getItemViewType(position: Int): Int {
        return if (position%3==0){
            LAYOUT_ONE
        }else{
            LAYOUT_TWO
        }
    }
    override fun onBindViewHolder(holder: PhotosAdapter.VH, position: Int) {
        holder.bind(photos?.get(position))

    }

    override fun getItemCount() = photos?.size ?: 0
    inner class VH(itemView : View) : RecyclerView.ViewHolder(itemView){

        init {
            with(itemView){

            }
        }

        fun bind(data: Photos?) {
            with(itemView){
                Picasso.get().load(data?.thumbnailUrl).into(imageview)
            }
        }
    }
}