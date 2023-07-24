package com.githubnames.domain.user

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.githubnames.data.dto.UserDTO
import com.githubnames.data.repositories.UserRepository

private const val DEFAULT_KEY = 1
private const val NUMBER_ONE = 1
private const val NEGATIVE_ONE = 1

class UserPagingSource(
    private val userRepository: UserRepository
) : PagingSource<Int, UserDTO>() {

    override fun getRefreshKey(state: PagingState<Int, UserDTO>) = null

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UserDTO> {
        return try {
            val currentPage = params.key ?: DEFAULT_KEY
            val response = userRepository.getAllUsers()
            LoadResult.Page(
                data = response,
                prevKey = getCurrentPage(currentPage),
                nextKey = getNextPage(currentPage)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    private fun getNextPage(currentPage: Int): Int {
        return currentPage.plus(NUMBER_ONE)
    }

    private fun getCurrentPage(currentPage: Int) =
        if (currentPage == NUMBER_ONE) null else NEGATIVE_ONE
}
