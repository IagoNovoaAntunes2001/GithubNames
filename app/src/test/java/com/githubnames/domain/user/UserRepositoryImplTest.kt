package com.githubnames.domain.user

import com.githubnames.data.api.UserRemote
import com.githubnames.data.dto.UserDTO
import com.githubnames.data.repositories.UserRepository
import com.githubnames.domain.user.Mock.getUsersDto
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class UserRepositoryImplTest {

    private lateinit var userRepository: UserRepository
    private lateinit var mockUserRemote: UserRemote

    @Before
    fun setUp() {
        mockUserRemote = mock(UserRemote::class.java)
        userRepository = UserRepositoryImpl(mockUserRemote)
    }

    @Test
    fun `when getAllUsers should return a list of users`() = runBlocking {
        val mockUsers = getUsersDto()
        `when`(mockUserRemote.getAllUsers()).thenReturn(mockUsers)

        val result = userRepository.getAllUsers()

        assertEquals(mockUsers, result)
    }

    @Test
    fun `when getAllUsers and remote returns empty should return an empty list`() = runBlocking {
        `when`(mockUserRemote.getAllUsers()).thenReturn(emptyList())

        val result = userRepository.getAllUsers()

        assertEquals(emptyList<UserDTO>(), result)
    }

    @Test
    fun `when getAllUsers and remote returns null should return null`() = runBlocking {
        `when`(mockUserRemote.getAllUsers()).thenReturn(null)

        val result = userRepository.getAllUsers()

        assertEquals(null, result)
    }
}