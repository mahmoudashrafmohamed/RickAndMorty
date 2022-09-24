package com.mahmoudashraf.home.presentation.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import com.mahmoudashraf.core.viewbinding.viewBinding
import com.mahmoudashraf.home.R
import com.mahmoudashraf.home.databinding.FragmentHomeBinding
import com.mahmoudashraf.home.presentation.view.adapter.CharactersListAdapter
import com.mahmoudashraf.home.presentation.viewmodel.HomeScreenState
import com.mahmoudashraf.home.presentation.viewmodel.HomeViewModel
import com.orcas.entities.home.Character
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

  private val binding by viewBinding(FragmentHomeBinding::bind)
  private val viewModel: HomeViewModel by viewModels()
  private val adapter by lazy { CharactersListAdapter() }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initView()
  }

  private fun initView() {
    // sets VeilRecyclerView's properties
    binding.veilRecyclerView.run {
      setVeilLayout(R.layout.item_character)
      setAdapter(adapter)
      setLayoutManager(GridLayoutManager(context, 2))
      addVeiledItems(15)
      observeScreenState()
    }
    adapter.setItemClickListener {
      Log.e("clicked", "" + it.name)
    }
  }

  private fun observeScreenState() {
    viewLifecycleOwner.lifecycleScope.launch {
      repeatOnLifecycle(Lifecycle.State.STARTED) {
        viewModel.uiState.collect { state ->
          when (state) {
            is HomeScreenState.Initial -> Unit
            is HomeScreenState.Loading -> showLoading()
            is HomeScreenState.Success -> handleSuccessState(state.characters)
            is HomeScreenState.Error -> handleErrorState(state)
          }
        }
      }
    }
  }

  private fun handleErrorState(state: HomeScreenState.Error) {
    Toast.makeText(
      context,
      state.msg,
      Toast.LENGTH_LONG
    ).show()
  }

  private fun showLoading() {
    binding.veilRecyclerView.veil()
  }

  private fun hideLoading() {
    binding.veilRecyclerView.unVeil()
  }

  private fun handleSuccessState(characters: List<Character>) {
    hideLoading()
    adapter.list = characters
  }

}
