package com.lahsuak.apps.githubtest.ui.fragments

import android.os.Bundle
import android.view.View
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.lahsuak.apps.githubtest.R
import com.lahsuak.apps.githubtest.databinding.FragmentDetailsBinding
import com.lahsuak.apps.githubtest.utils.Constants

class DetailFragment : Fragment(R.layout.fragment_details) {

    private lateinit var binding: FragmentDetailsBinding
    private val args: DetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentDetailsBinding.bind(view)

        binding.repoStar.text = getString(R.string.star_count,args.repo.starCount)

        val url = Constants.domainURL+ args.repo.fullName + "/blob/master/README.md"

        binding.repoReadme.apply {
            loadUrl(url)
            webViewClient = WebViewClient()
        }

    }
}