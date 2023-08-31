package com.githubnames.domain.user

import androidx.paging.PagingSource
import com.githubnames.data.repositories.UserRepository
import com.githubnames.domain.user.Mock.getUsers
import com.githubnames.domain.user.Mock.getUsersDto
import com.githubnames.domain.user.converter.UserDataConverter
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

@ExperimentalCoroutinesApi
class UserPagingSourceTest {

    private lateinit var userPagingSource: UserPagingSource
    private val mockUserRepository: UserRepository = mock(UserRepository::class.java)
    private val mockUserDataConverter: UserDataConverter = mock(UserDataConverter::class.java)

    private val testDispatcher: TestDispatcher = StandardTestDispatcher()
    private val testScope: TestScope = TestScope(testDispatcher)

    @Before
    fun setUp() {
        userPagingSource = UserPagingSource(mockUserRepository, mockUserDataConverter)
    }

    @Test
    fun `when load should return a LoadResultPage with data`() = testScope.runTest {
        val mockUsers = getUsersDto()
        `when`(mockUserRepository.getAllUsers()).thenReturn(mockUsers)
        `when`(mockUserDataConverter.convert(mockUsers)).thenReturn(getUsers())


        val params: PagingSource.LoadParams<Int> =
            mock(PagingSource.LoadParams::class.java) as PagingSource.LoadParams<Int>
        val result = userPagingSource.load(params)

        assertEquals(
            PagingSource.LoadResult.Page(
                data = getUsers(),
                prevKey = null,
                nextKey = 2
            ),
            result
        )
    }

    @Test(expected = Exception::class)
    fun `when load should return a LoadResultError when an exception is thrown`() =
        testScope.runTest {
            val exception = Exception("Something went wrong")

            `when`(mockUserRepository.getAllUsers()).thenThrow(exception)

            val params: PagingSource.LoadParams<Int> =
                mock(PagingSource.LoadParams::class.java) as PagingSource.LoadParams<Int>

            userPagingSource.load(params)
        }
}