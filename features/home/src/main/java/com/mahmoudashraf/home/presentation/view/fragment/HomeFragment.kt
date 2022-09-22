package com.mahmoudashraf.home.presentation.view.fragment

import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.mahmoudashraf.core.viewbinding.viewBinding
import com.mahmoudashraf.home.R
import com.mahmoudashraf.home.databinding.FragmentHomeBinding
import com.mahmoudashraf.home.presentation.view.adapter.CharactersListAdapter
import com.mahmoudashraf.home.presentation.viewmodel.HomeViewModel
import com.orcas.entities.home.Character
import com.orcas.entities.home.Location
import com.orcas.entities.home.Origin
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private val binding by viewBinding(FragmentHomeBinding::bind)
    private val viewModel: HomeViewModel by viewModels()
    private val adapter by lazy { CharactersListAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // sets VeilRecyclerView's properties
        binding.veilRecyclerView.run {
            setVeilLayout(R.layout.item_character)
            setAdapter(adapter)
            setLayoutManager(GridLayoutManager(context, 2))
            addVeiledItems(15)
            viewModel.call()
            Handler().postDelayed({
                adapter.list = listOf(
                    Character(
                        1, "kkkkkkk", "", "", "",
                        "", Origin("", ""), Location("", ""), "https://assets.goal.com/v3/assets/bltcc7a7ffd2fbf71f5/bltbb6a2b45de4350a1/60dc218776a3de39ad178c00/b866411a7b6ee04a29e1b5fa02a9f12dc38bf061.jpg", listOf(), "", ""
                    ),  Character(
                        12, "ccccccccccccccccccccc", "", "", "",
                        "", Origin("", ""), Location("", ""), "", listOf(), "", ""
                    )
                )

                unVeil()
            }, 2000)
        }
    }
}
