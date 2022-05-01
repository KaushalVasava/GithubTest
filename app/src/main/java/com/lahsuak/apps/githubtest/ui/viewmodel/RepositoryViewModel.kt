package com.lahsuak.apps.githubtest.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lahsuak.apps.githubtest.model.Repository
import com.lahsuak.apps.githubtest.network.GithubApi
import com.lahsuak.apps.githubtest.utils.Constants.SORT_BY_STAR
import com.lahsuak.apps.githubtest.utils.Constants.SORT_DESC
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class RepositoryViewModel @Inject constructor(
    private val api: GithubApi,
) : ViewModel() {

    private val _repositories = MutableLiveData<List<Repository>>()
    val repositories: LiveData<List<Repository>>
        get() = _repositories

    fun getData(query: String) {
        viewModelScope.launch {
            try {
                _repositories.value =
                    api.getSearchResult(query, 1, 100, SORT_BY_STAR, SORT_DESC).items
            } catch (e: Exception) {
               e.printStackTrace()
            }
        }
    }

}