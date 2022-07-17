package com.example.reddit.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.reddit.data.api.ApiService
import com.example.reddit.model.ModelPost
import retrofit2.HttpException
import java.io.IOException

class PagingPublication(private val apiService: ApiService) : PagingSource<String, ModelPost>() {

    override val keyReuseSupported: Boolean = true

    override suspend fun load(params: LoadParams<String>): LoadResult<String, ModelPost> {
        return try {
            val response = apiService.getTop(loadSize = params.loadSize, after = params.key)
            val listing = response.body()?.data
            val redditPosts =
                listing?.children?.map { it.data }
            LoadResult.Page(
                redditPosts ?: listOf(),
                listing?.before,
                listing?.after
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<String, ModelPost>): String? {
        TODO("Not yet implemented")
    }
}
