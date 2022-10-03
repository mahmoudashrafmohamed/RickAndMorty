package com.mahmoudashraf.details.presentation.view.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.mahmoudashraf.core.constants.NavigationConstants
import com.mahmoudashraf.core.viewbinding.viewBinding
import com.mahmoudashraf.details.R
import com.mahmoudashraf.details.databinding.FragmentDetailsBinding
import com.mahmoudashraf.entities.home.Character
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment(R.layout.fragment_details) {
    private val binding by viewBinding(FragmentDetailsBinding::bind)
    private val character by lazy { arguments?.getParcelable<Character>(NavigationConstants.CHARACTER) }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        character?.let {
            binding.tvCharacterName.text = it.name
            Glide.with(binding.imgCharacter).load(it.image).into(binding.imgCharacter)
            binding.tvGender.text = it.gender
            binding.tvSpecies.text = it.species
            binding.tvStatus.text = it.status
            binding.tvLocation.text = it.locationText
        }
    }
}