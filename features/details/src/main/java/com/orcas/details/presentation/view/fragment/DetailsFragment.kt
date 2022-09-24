package com.orcas.details.presentation.view.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.mahmoudashraf.core.constants.NavigationConstants
import com.mahmoudashraf.core.viewbinding.viewBinding
import com.orcas.details.R
import com.orcas.details.databinding.FragmentDetailsBinding
import com.orcas.entities.home.Character
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment(R.layout.fragment_details) {
    private val binding by viewBinding(FragmentDetailsBinding::bind)
    val character by lazy { arguments?.getParcelable<Character>(NavigationConstants.CHARACTER) }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        character?.let {
            val desc = "${it.name} ${it.location.name}"
            binding.tvDesc.text = desc
        }
    }
}