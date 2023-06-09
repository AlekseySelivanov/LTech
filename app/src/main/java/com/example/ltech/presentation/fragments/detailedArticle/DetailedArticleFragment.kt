package com.example.ltech.presentation.fragments.detailedArticle

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.ltech.R
import com.example.ltech.databinding.FragmentDetailedArticleBinding
import dagger.hilt.android.AndroidEntryPoint
import com.example.ltech.utils.Constants.BASE_URL
import com.example.ltech.utils.formatDate
import com.example.ltech.utils.makeToast

@AndroidEntryPoint
class DetailedArticleFragment : Fragment(R.layout.fragment_detailed_article) {

    private val binding by viewBinding(FragmentDetailedArticleBinding::bind)
    private val args: DetailedArticleFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d("Articles Worker", " fragment2")
        super.onViewCreated(view, savedInstanceState)
        setDataToFragment()
        setMenuToDetailedFragment()
    }

    private fun setMenuToDetailedFragment() {
        val toolbar = binding.detailedArticlesListToolbar
        toolbar.apply {
            binding.apply {
                setNavigationOnClickListener {
                    findNavController().navigateUp()
                }
                inflateMenu(R.menu.detailed_fragment_action_bar)
                setOnMenuItemClickListener {
                    when (it.itemId) {
                        R.id.shareMenuItem -> {
                            makeToast(getString(R.string.invalid_share))
                        }
                        R.id.favoritesMenuItem -> {
                            makeToast(getString(R.string.invalid_favorites))
                        }
                    }
                    false
                }
            }
        }
    }

    private fun setDataToFragment() {
        val article = args.article
        binding.apply {
            detailedArticleTitle.text = article.title
            detailedArticleDate.text = article.formatDate(article.date)
            detailedArticleText.text = article.text
            Glide.with(this.root)
                .load(BASE_URL + article.image)
                .error(R.drawable.ic_baseline_close_24)
                .centerCrop()
                .into(detailedItemImage)
        }
    }
}