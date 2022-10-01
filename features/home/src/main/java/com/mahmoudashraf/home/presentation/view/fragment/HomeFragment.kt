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
import androidx.recyclerview.widget.RecyclerView
import com.mahmoudashraf.core.view.pagination.EndlessRecyclerViewScrollListener
import com.mahmoudashraf.core.viewbinding.viewBinding
import com.mahmoudashraf.home.R
import com.mahmoudashraf.home.databinding.FragmentHomeBinding
import com.mahmoudashraf.home.presentation.view.adapter.CharactersListAdapter
import com.mahmoudashraf.home.presentation.view.navigation.HomeActions
import com.mahmoudashraf.home.presentation.viewmodel.HomeScreenState
import com.mahmoudashraf.home.presentation.viewmodel.HomeViewModel
import com.mahmoudashraf.entities.home.Character
import com.mahmoudashraf.home.presentation.model.BaseCharacterUIModel
import com.mahmoudashraf.home.presentation.model.CharacterUIModel
import com.mahmoudashraf.home.presentation.model.LoadingItemUIModel
import com.mahmoudashraf.local.entities.CharacterLocalEntity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

  private val binding by viewBinding(FragmentHomeBinding::bind)
  private val viewModel: HomeViewModel by viewModels()
  private val adapter by lazy { CharactersListAdapter() }
  @Inject
  lateinit var homeActions: HomeActions

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
      binding.veilRecyclerView.getRecyclerView().addOnScrollListener(object :
        EndlessRecyclerViewScrollListener(binding.veilRecyclerView.getRecyclerView().layoutManager as GridLayoutManager) {
        override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
          Log.e("load more", "----------")
          viewModel.getCharacters(page+1)
        }
      })
      observeScreenState()
    }
    adapter.setItemClickListener {
      val characterUIModel = it as CharacterUIModel
      homeActions.navigateToDetails(Character(characterUIModel.id, characterUIModel.name,characterUIModel.image))
    }
  }

  private fun observeScreenState() {
    viewLifecycleOwner.lifecycleScope.launch {
      repeatOnLifecycle(Lifecycle.State.STARTED) {
        viewModel.uiState.collect { state ->
          when (state) {
            is HomeScreenState.Initial -> Unit
            is HomeScreenState.Loading -> showLoading()
            is HomeScreenState.LoadingNextPage -> handleShowFooterProgress(state.characters)
            is HomeScreenState.Success -> handleSuccessState(state.characters)
            is HomeScreenState.Error -> handleErrorState(state)
          }
        }
      }
    }
  }

  private fun handleShowFooterProgress(characters: List<BaseCharacterUIModel>) {
    adapter.list = characters
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

  private fun handleSuccessState(characters: List<BaseCharacterUIModel>) {
    hideLoading()
    adapter.list = characters
  }

  override fun onPause() {
    super.onPause()
    binding.veilRecyclerView.getRecyclerView().layoutManager?.onSaveInstanceState()?.let { viewModel.saveRecyclerViewState(it) }
  }

  override fun onResume() {
    super.onResume()
    if (viewModel.stateInitialized()) {
      binding.veilRecyclerView.getRecyclerView().layoutManager?.onRestoreInstanceState(viewModel.restoreRecyclerViewState())
    }
  }

}
