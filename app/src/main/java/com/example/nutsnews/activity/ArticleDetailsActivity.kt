package com.example.nutsnews.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.nutsnews.R
import com.example.nutsnews.core.Constant
import com.example.nutsnews.data.local.entity.ArticlesEntity
import kotlinx.android.synthetic.main.activity_article_details.*


class ArticleDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_details)
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        setupContent()
    }

    fun setupContent(){
        val articlesEntity: ArticlesEntity? = intent.extras?.getParcelable(Constant.ARTICLE)
        Glide
            .with(this)
            .load(articlesEntity?.urlToImage)
            .placeholder(R.color.silver2)
            .into(imgArticle);
        articleTitle.text = articlesEntity?.title
        articlePublishDate.text = "Date: " + articlesEntity?.publishedAt?.substring(0, 10)
        articleContent.text = articlesEntity?.content
        buttonBack.setOnClickListener {
            finish()
        }
    }
}
