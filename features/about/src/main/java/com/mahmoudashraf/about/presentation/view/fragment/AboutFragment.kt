package com.mahmoudashraf.about.presentation.view.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.mahmoudashraf.about.R
import com.mahmoudashraf.about.databinding.FragmentAboutBinding
import com.mahmoudashraf.core.BuildConfig
import com.mahmoudashraf.core.viewbinding.viewBinding


class AboutFragment : Fragment(R.layout.fragment_about) {
    private val binding by viewBinding(FragmentAboutBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val appVersion = BuildConfig.VERSION_NAME
        binding.tvAppVersion.text = appVersion
    }
}
