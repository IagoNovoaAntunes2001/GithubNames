package com.githubnames.domain.user

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.githubnames.data.api.UserRemote
import com.githubnames.data.dto.UserDTO

private const val DEFAULT_KEY = 1
private const val NUMBER_ONE = 1
private const val NEGATIVE_ONE = 1

class UserPagingSource(
    private val userRemote: UserRemote
) : PagingSource<Int, UserDTO>() {

    override fun getRefreshKey(state: PagingState<Int, UserDTO>) = null

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UserDTO> {
        return try {
            val currentPage = params.key ?: DEFAULT_KEY
            val response = userRemote.getAllUsers()
            LoadResult.Page(
                data = response,
                prevKey = getCurrentPage(currentPage),
                nextKey = currentPage.plus(NUMBER_ONE)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    private fun getCurrentPage(currentPage: Int) =
        if (currentPage == NUMBER_ONE) null else NEGATIVE_ONE
}
