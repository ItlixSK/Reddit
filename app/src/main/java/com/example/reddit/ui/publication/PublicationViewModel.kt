package com.example.reddit.ui.publication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.reddit.data.paging.PagingPublication
import com.example.reddit.data.retrofit.RetrofitReddit

class PublicationViewModel : ViewModel() {

    val posts = Pager(PagingConfig(pageSize = 10, enablePlaceholders = false))
    {
        PagingPublication(RetrofitReddit.getApiServiceRetrofit())
    }.flow.cachedIn(viewModelScope)
}