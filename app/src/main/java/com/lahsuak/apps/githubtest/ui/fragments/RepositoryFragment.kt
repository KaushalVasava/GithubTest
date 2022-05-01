package com.lahsuak.apps.githubtest.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.lahsuak.apps.githubtest.R
import com.lahsuak.apps.githubtest.databinding.FragmentRepositoryBinding
import com.lahsuak.apps.githubtest.model.Repository
import com.lahsuak.apps.githubtest.ui.adapter.RepositoryAdapter
import com.lahsuak.apps.githubtest.ui.viewmodel.RepositoryViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RepositoryFragment : Fragment(R.layout.fragment_repository),
    RepositoryAdapter.ItemClickListener {

    private lateinit var binding: FragmentRepositoryBinding

    private lateinit var adapter: RepositoryAdapter
    private val viewModel:RepositoryViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRepositoryBinding.bind(view)


        adapter = RepositoryAdapter(this)
        binding.rvRepository.setHasFixedSize(true)
        binding.rvRepository.adapter = adapter

        viewModel.getData("kotlin")

        val data = mutableListOf<Repository>()
        viewModel.repositories.observe(viewLifecycleOwner) {
            for (item in it) {
                if (item.language == null || item.language.toString().lowercase() != "kotlin") {
                    //do nothing
                } else {
                    data.add(item)
                }
            }
            adapter.submitList(data)
            binding.rvRepository.isVisible = true
            binding.pbRepo.isGone = true
        }
    }

    override fun onItemClick(position: Int) {
        val action =
            RepositoryFragmentDirections.actionRepositoryFragmentToDetailFragment(adapter.currentList[position])
        findNavController().navigate(action)
    }
}