package com.example.taveconnect

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.taveconnect.databinding.FragmentBurgerBinding

class BurgerFragment : Fragment(R.layout.fragment_burger) {
    private var _binding: FragmentBurgerBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBurgerBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }
}