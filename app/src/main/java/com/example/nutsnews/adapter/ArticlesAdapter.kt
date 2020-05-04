package com.example.nutsnews.adapter

import android.content.Intent
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.nutsnews.R
import com.example.nutsnews.activity.ArticleDetailsActivity
import com.example.nutsnews.core.Constant
import com.example.nutsnews.core.inflate
import com.example.nutsnews.data.local.entity.ArticlesEntity
import kotlinx.android.synthetic.main.row_article_item.view.*

class ArticlesAdapter : ListAdapter<ArticlesEntity, ArticlesAdapter.NewsHolder>(DIFF_CALLBACK) {

    /**
     * Inflate the view
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        NewsHolder(parent.inflate(R.layout.row_article_item))

    /**
     * Bind the view with the data
     */
    override fun onBindViewHolder(newsHolder: NewsHolder, position: Int) =
        newsHolder.bind(getItem(position))

    /**
     * View Holder Pattern
     */
    class NewsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        /**
         * Binds the UI with the data and handles clicks
         */
        fun bind(newsArticle: ArticlesEntity) =
            with(itemView) {
                newsTitle.text = newsArticle.title
                newsAuthor.text = newsArticle.author
                newsPublishedAt.text = "Date: " + newsArticle?.publishedAt?.substring(0, 10)

                Glide
                    .with(itemView.context)
                    .load(newsArticle.urlToImage)
                    .placeholder(R.color.silver2)
                    .into(newsImage);

                setOnClickListener {
                    val intent = Intent(itemView.context, ArticleDetailsActivity::class.java)
                    val newsArticle = newsArticle
                    intent.putExtra(Constant.ARTICLE, newsArticle)
                    itemView.context.startActivity(intent)
                }
            }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ArticlesEntity>() {
            override fun areItemsTheSame(
                oldItem: ArticlesEntity,
                newItem: ArticlesEntity
            ): Boolean = oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: ArticlesEntity,
                newItem: ArticlesEntity
            ): Boolean = oldItem == newItem
        }
    }
}