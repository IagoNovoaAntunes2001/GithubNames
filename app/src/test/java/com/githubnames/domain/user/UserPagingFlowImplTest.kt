package com.githubnames.domain.user

import androidx.paging.PagingData
import com.githubnames.data.entities.User
import com.githubnames.data.repositories.UserPagingFlow
import com.githubnames.data.repositories.UserRepository
import com.githubnames.domain.user.Mock.getUsersDto
import com.githubnames.domain.user.converter.UserDataConverter
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

@ExperimentalCoroutinesApi
class UserPagingFlowImplTest {

    private lateinit var userPagingFlow: UserPagingFlow
    private val mockUserRepository: UserRepository = mock(UserRepository::class.java)
    private val mockUserDataConverter: UserDataConverter = mock(UserDataConverter::class.java)

    private val testDispatcher: TestDispatcher = StandardTestDispatcher()
    private val testScope: TestScope = TestScope(testDispatcher)

    @Before
    fun setUp() {
        userPagingFlow = UserPagingFlowImpl(mockUserRepository, mockUserDataConverter)
    }

    @Test
    fun `when getPagingData should return a flow with mocked users`() =
        testScope.runTest {
            `when`(mockUserRepository.getAllUsers()).thenReturn(getUsersDto())
            `when`(mockUserDataConverter.convert(getUsersDto()))
                .thenReturn(mockUserDataConverter.convert(getUsersDto()))

            val resultFlow: Flow<PagingData<User>> = userPagingFlow.getPagingData()

            resultFlow.collect { resultPagingData ->
                assertNotNull(resultPagingData)
            }
        }
}
