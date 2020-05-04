package com.example.nutsnews.activity

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nutsnews.R
import com.example.nutsnews.adapter.ArticlesAdapter
import com.example.nutsnews.core.getViewModel
import com.example.nutsnews.core.observeNotNull
import com.example.nutsnews.core.toast
import com.example.nutsnews.data.ViewState
import com.example.nutsnews.viewmodel.ArticleViewModel
import kotlinx.android.synthetic.main.activity_main.*

class ArticleActivity : BaseActivity() {

    private val newsArticleViewModel by lazy { getViewModel<ArticleViewModel>() }
    lateinit var adapter: ArticlesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        adapter = ArticlesAdapter()
        articalList.adapter = adapter
        articalList.layoutManager = LinearLayoutManager(this)

        // Update the UI on state change
        handleAdapterData()

        refreshButton.setOnClickListener {
            handleAdapterData()
        }
    }

    fun handleAdapterData() {
        newsArticleViewModel.getArticles().observeNotNull(this) { state ->
            when (state) {
                is ViewState.Success -> {
                    hideLoader()
                    adapter.submitList(state.data)}
                is ViewState.Loading -> {
                    showLoader()
                }
                is ViewState.Error -> {toast(state.message)}
            }
        }
    }

    /**
     * Hide loader on data receive
     */
    fun hideLoader(){
        loadingContainer.stopShimmerAnimation()
        errorScreen.visibility = View.GONE;
        loadingContainer.visibility = View.GONE
    }

    /**
     * Show loader
     */
    fun showLoader(){
        loadingContainer.startShimmerAnimation()
        errorScreen.visibility = View.GONE;
        loadingContainer.visibility = View.VISIBLE
    }
}
