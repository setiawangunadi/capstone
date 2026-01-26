package com.setiawan.capstone.core.presentation

import androidx.recyclerview.widget.ListAdapter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.setiawan.capstone.core.R
import com.setiawan.capstone.core.databinding.ItemListMovieBinding
import com.setiawan.capstone.core.domain.model.Movie

class MovieAdapter : ListAdapter<Movie, MovieAdapter.ListViewHolder>(DIFF_CALLBACK) {

    var onItemClick: ((Movie) -> Unit)? = null
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = ListViewHolder(
        ItemListMovieBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(
        holder: ListViewHolder,
        position: Int
    ) {
        val data = getItem(position)
        holder.bind(data)
    }

    inner class ListViewHolder(private var binding: ItemListMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Movie) {
            val baseUrl = "https://image.tmdb.org/t/p/w500"
            Glide.with(itemView.context)
                .load(baseUrl + data.backdropPath)
                .placeholder(R.drawable.side_nav_bar)
                .into(binding.ivItemImage)
            binding.tvItemTitle.text = data.title
            binding.tvItemSubtitle.text = data.overview
        }

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(getItem(bindingAdapterPosition))
            }
        }
    }

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<Movie> =
            object : DiffUtil.ItemCallback<Movie>() {
                override fun areItemsTheSame(
                    oldItem: Movie,
                    newItem: Movie
                ): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(
                    oldItem: Movie,
                    newItem: Movie
                ): Boolean {
                    return oldItem == newItem
                }
            }
    }
}