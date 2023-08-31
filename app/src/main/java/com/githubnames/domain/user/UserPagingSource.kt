package com.githubnames.domain.user

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.githubnames.data.entities.User
import com.githubnames.data.repositories.UserRepository
import com.githubnames.domain.user.converter.UserDataConverter

private const val DEFAULT_KEY = 1
private const val NUMBER_ONE = 1
private const val NEGATIVE_ONE = 1
private const val LAST_ID = 46

class UserPagingSource(
    private val userRepository: UserRepository,
    private val userDataConverter: UserDataConverter
) : PagingSource<Int, User>() {

    override fun getRefreshKey(state: PagingState<Int, User>) = null

    override suspend fun load(params: LoadParams<Int>) = try {
        val currentPage = params.key ?: DEFAULT_KEY
        val response = userDataConverter.convert(userRepository.getAllUsers())
        LoadResult.Page(
            data = response,
            prevKey = getCurrentPage(currentPage),
            nextKey = getNextPage(currentPage, isLastPage(response))
        )
    } catch (e: Exception) {
        LoadResult.Error(e)
    }

    private fun isLastPage(response: List<User>) = response.find {
        it.id.toInt() == LAST_ID
    } != null

    private fun getNextPage(currentPage: Int, lastPage: Boolean) = when {
        lastPage -> null
        else -> currentPage.plus(NUMBER_ONE)
    }

    private fun getCurrentPage(currentPage: Int) =
        if (currentPage == NUMBER_ONE) null else NEGATIVE_ONE
}
