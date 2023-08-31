package com.githubnames.presentation.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.githubnames.R
import com.githubnames.databinding.FragmentUserBinding
import com.githubnames.presentation.user.adapter.UserAdapter
import com.githubnames.presentation.user.viewmodel.UserViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserFragment : Fragment(R.layout.fragment_user) {

    private var _binding: FragmentUserBinding? = null
    private val binding get() = _binding!!

    private val userViewModel: UserViewModel by viewModel()
    private var userAdapter: UserAdapter? = null

    companion object {
        private const val SPAN_COUNT = 2
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUsersList()
        loadingData()
        setupScreenListeners()
    }

    private fun loadingData() {
        lifecycleScope.launch {
            userViewModel.listData.collect { pagingData ->
                userAdapter?.submitData(pagingData)
            }
        }
    }

    private fun setupScreenListeners() {
        stateAdapterListener()
        swipeListener()
    }

    private fun stateAdapterListener() {
        userAdapter?.addLoadStateListener { loadState ->
            loadingStateListener(loadState)
            errorStateListener(loadState)
        }
    }

    private fun errorStateListener(loadState: CombinedLoadStates) {
        if (isErrorState(loadState)) {
            binding.messageError.visibility = View.VISIBLE
            binding.userList.visibility = View.GONE
        }
    }

    private fun isErrorState(loadState: CombinedLoadStates) = loadState.refresh is LoadState.Error
    private fun loadingStateListener(loadState: CombinedLoadStates) {
        val isRefreshing = loadState.refresh is LoadState.Loading
        binding.loading.visibility = setLoadingVisibility(isRefreshing)
        binding.refreshLayout.isRefreshing = isRefreshing
    }

    private fun setLoadingVisibility(isRefreshing: Boolean) =
        if (isRefreshing) View.VISIBLE else View.GONE

    private fun swipeListener() {
        binding.refreshLayout.setOnRefreshListener {
            userAdapter?.refresh()
        }
    }

    private fun setupUsersList() {
        userAdapter = UserAdapter()
        binding.userList.apply {
            layoutManager = StaggeredGridLayoutManager(
                SPAN_COUNT, StaggeredGridLayoutManager.VERTICAL
            )
            adapter = userAdapter
            setHasFixedSize(true)
        }
    }
}
