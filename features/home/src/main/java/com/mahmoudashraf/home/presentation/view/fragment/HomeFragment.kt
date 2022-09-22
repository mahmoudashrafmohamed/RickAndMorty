package com.mahmoudashraf.home.presentation.view.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.mahmoudashraf.core.viewbinding.viewBinding
import com.mahmoudashraf.home.R
import com.mahmoudashraf.home.data.source.remote.CharactersRemoteDataSourceImpl
import com.mahmoudashraf.home.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

  private val binding by viewBinding(FragmentHomeBinding::bind)

  @Inject
  lateinit var charactersRemoteDataSourceImpl: CharactersRemoteDataSourceImpl

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    // sets VeilRecyclerView's properties
    binding.veilRecyclerView.run {
      setVeilLayout(R.layout.item_character)
     // setAdapter(adapter)
      setLayoutManager(LinearLayoutManager(context))
      addVeiledItems(15)
    }

    GlobalScope.launch {
      charactersRemoteDataSourceImpl.getCharacters()
    }
  }
}
