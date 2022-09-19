package com.mahmoudashraf.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.mahmoudashraf.core.viewbinding.viewBinding
import com.mahmoudashraf.home.databinding.FragmentHomeBinding

class HomeFragment : Fragment(R.layout.fragment_home) {

  private val binding by viewBinding(FragmentHomeBinding::bind)

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

  }
}
