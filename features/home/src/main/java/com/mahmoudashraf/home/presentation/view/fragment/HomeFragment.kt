package com.mahmoudashraf.home.presentation.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import com.mahmoudashraf.core.androidextensions.getMessageShouldDisplay
import com.mahmoudashraf.core.androidextensions.showErrorSnackBar
import com.mahmoudashraf.core.exceptions.RickAndMortyException
import com.mahmoudashraf.core.view.pagination.setOnLoadMoreListener
import com.mahmoudashraf.core.viewbinding.viewBinding
import com.mahmoudashraf.home.R
import com.mahmoudashraf.home.databinding.FragmentHomeBinding
import com.mahmoudashraf.home.presentation.model.BaseCharacterUIModel
import com.mahmoudashraf.home.presentation.model.CharacterUIModel
import com.mahmoudashraf.home.presentation.view.adapter.CharactersListAdapter
import com.mahmoudashraf.home.presentation.view.navigation.HomeActions
import com.mahmoudashraf.home.presentation.viewmodel.HomeScreenState
import com.mahmoudashraf.home.presentation.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.mahmoudashraf.core.R as CoreStrings

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

  private val binding by viewBinding(FragmentHomeBinding::bind)
  private val viewModel: HomeViewModel by viewModels()
  private val adapter by lazy { CharactersListAdapter() }
  private val charactersRvSpanSizeLookup by lazy {
    object : GridLayoutManager.SpanSizeLookup() {
      override fun getSpanSize(position: Int): Int {
        return when (adapter.getItemViewType(position)) {
          R.layout.item_character -> 1
          R.layout.item_loading -> 2 // number of columns of the grid
          else -> -1
        }
      }
    }
  }

  @Inject
  lateinit var homeActions: HomeActions

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    (activity as? AppCompatActivity)?.setSupportActionBar(binding.toolbar)
    setHasOptionsMenu(true)
    initView()
  }

  private fun initView() {
    // sets VeilRecyclerView's properties
    binding.veilRecyclerView.run {
      setVeilLayout(R.layout.item_character)
      setAdapter(adapter)
      setLayoutManager(
        GridLayoutManager(context, 2)
          .also {
            it.spanSizeLookup = charactersRvSpanSizeLookup
          }
      )
      addVeiledItems(15)
      binding.veilRecyclerView.getRecyclerView().setOnLoadMoreListener {
        Log.e("load more", "----------")
        viewModel.getCharacters()
      }
      observeScreenState()
    }
    adapter.setItemClickListener {
      val characterUIModel = it as CharacterUIModel
      homeActions.navigateToDetails(viewModel.mapToCharacterEntity(characterUIModel))
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
            is HomeScreenState.Error -> handleErrorState(state.exception)
          }
        }
      }
    }
  }

  private fun handleShowFooterProgress(characters: List<BaseCharacterUIModel>) {
    adapter.list = characters
  }

  private fun handleErrorState(exception: RickAndMortyException) {
    binding.root.showErrorSnackBar(
      errorMessage = getMessageShouldDisplay(exception),
      actionName = getString(CoreStrings.string.lbl_retry),
      actionClickListener = { viewModel.getCharacters() }
    )
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
    binding.veilRecyclerView.getRecyclerView().layoutManager?.onSaveInstanceState()
      ?.let { viewModel.saveRecyclerViewState(it) }
  }

  override fun onResume() {
    super.onResume()
    if (viewModel.stateInitialized()) {
      binding.veilRecyclerView.getRecyclerView().layoutManager?.onRestoreInstanceState(
        viewModel.restoreRecyclerViewState()
      )
    }
  }

  override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
    inflater.inflate(R.menu.home_menu, menu)
    // Set the item state
    viewLifecycleOwner.lifecycleScope.launch {
      repeatOnLifecycle(Lifecycle.State.STARTED) {
        viewModel.getUIMode.collect {
          val item = menu.findItem(R.id.action_night_mode)
          if (it) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            item.setIcon(R.drawable.ic_night)
          } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            item.setIcon(R.drawable.ic_day)
          }
        }
      }
    }
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    return when (item.itemId) {
      R.id.action_night_mode -> {
        viewLifecycleOwner.lifecycleScope.launch {
          val isChecked = viewModel.getUIMode.first()
          viewModel.saveToUIMode(isChecked.not())
        }
        true
      }
      R.id.action_about -> {
        homeActions.navigateToAbout()
        true
      }
      else -> super.onOptionsItemSelected(item)
    }
  }
}
